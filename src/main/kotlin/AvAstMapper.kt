import ast.*
import ast.EBind.Persistent
import ast.EBind.Temporary
import ast.EBorrow.Default
import ast.EBorrow.Differ
import ast.EBorrow.Intersect
import ast.EBorrow.Either
import ast.EBorrow.Push
import ast.EBorrow.Overlay
import ast.EBorrow.Unite
import org.antlr.v4.runtime.tree.TerminalNode
import org.fancryer.gen.AvParser.*
import org.fancryer.gen.AvParserBaseVisitor

class AvAstMapper:AvParserBaseVisitor<AvNode>()
{
	fun visitVar_exp(id:TerminalNode?,string:StringContext?):AvExp=
		AvVarExp(idOrString(id,string))

	fun visitExp(ctx:ExpContext):AvExp=
		when(ctx)
		{
			is Atom_expContext->visitAtom(ctx.atom())
			is List_expContext->visitList(ctx.list())
			is Bytes_expContext->visitBytes(ctx.bytes())
			is Ref_expContext->visitRef_exp(ctx)
			is Borrow_expContext->visitBorrow_exp(ctx)
			is Var_expContext->visitVar_exp(ctx.Id(),ctx.string())
			is Map_expContext->visitMap_exp(ctx)
			else->error("Unreachable")
		}

	override fun visitBytes_exp(ctx:Bytes_expContext):AvBytes=visitBytes(ctx.bytes())

	override fun visitList_exp(ctx:List_expContext):AvList=visitList(ctx.list())

	override fun visitMap_exp(ctx:Map_expContext):AvMap=visitMap(ctx.map())

	override fun visitBorrow_exp(ctx:Borrow_expContext):AvBorrowExp
	{
		val left=visitExp(ctx.exp(0))
		val right=visitExp(ctx.exp(1))
		val borrow=visitBorrow(ctx.borrow())
		return AvBorrowExp(left,borrow,right)
	}

	override fun visitAtom(ctx:AtomContext):AvAtom=
		ctx.run {
			//atom: int | Float | string | True | False | Nil | Id;
			int_()?.let(::visitInt)
			?: Float()?.let {AvFloat(it.text.toFloat())}
			?: string()?.let(::visitString)?.let {
				if(it.contents.size==1)
				{
					when(val first=it.contents[0])
					{
						is AvText->AvConstantString(first.value)
						else->it
					}
				}
				else it
			}
			?: True()?.let {AvTrue}
			?: False()?.let {AvFalse}
			?: Nil()?.let {AvNil}
			?: Id()?.let {AvId(it.text)}
			?: error("Unreachable (${ctx::class}): ${ParseTreeSourcifier().sourcify(ctx)}")
		}

	override fun visitRef_exp(ctx:Ref_expContext):AvRefExp=
		AvRefExp(visitExp(ctx.exp()),visitAtom(ctx.atom()))

	override fun visitChunk(ctx:ChunkContext):AvChunk=
		AvChunk(
			ctx.map()?.let(::visitMap)
			?: ctx.map_entries()?.let(::visitMap_entries)
			?: AvMap()
		)

	override fun visitBorrow(ctx:BorrowContext):EBorrow=
		ctx.matchNotNull(
			Push,
			{Overlay() to {Overlay}},
			{Unite() to {Unite}},
			{Default() to {Default}},
			{Intersect() to {Intersect}},
			{Differ() to {Differ}},
			{Either() to {Either}}
		)

	override fun visitAtom_exp(ctx:Atom_expContext):AvAtom=
		visitAtom(ctx.atom())

	override fun visitList_entry(ctx:List_entryContext):AvListEntry=
		ctx.exp()?.let(::visitExp)
		?: visitDecl_entry(ctx.decl_entry())

	override fun visitDecl_entry(ctx:Decl_entryContext):AvDeclEntry=
		AvDeclEntry(
			idOrString(ctx.Id(),ctx.string()),
			visitExp(ctx.exp()),
			ctx.BindTemporary()?.let {Temporary} ?: Persistent
		)

	private fun idOrString(id:TerminalNode?,string:StringContext?):AvStringLike=
		id?.text?.let(::AvId) ?: visitString(string!!)

	override fun visitList(ctx:ListContext):AvList=
		visitList_entries(ctx.list_entries())

	override fun visitList_entries(ctx:List_entriesContext?):AvList=
		AvList(ctx?.list_entry()?.map(::visitList_entry) ?: emptyList())


	override fun visitMap_entries(ctx:Map_entriesContext?):AvMap=
		AvMap(ctx?.map_entry()?.map(::visitMap_entry) ?: emptyList())

	override fun visitMap(ctx:MapContext):AvMap=
		visitMap_entries(ctx.map_entries())

	override fun visitMap_entry(ctx:Map_entryContext):AvMapEntry=
		ctx.plain_map_entry()?.let(::visitPlain_map_entry)
		?: visitDecl_entry(ctx.decl_entry())

	override fun visitPlain_map_entry(ctx:Plain_map_entryContext):AvPlainMapEntry=
		AvPlainMapEntry(
			visitAtom(ctx.atom()),
			visitExp(ctx.exp())
		)

	override fun visitBytes(ctx:BytesContext):AvBytes=
		ctx.HexInt().map(::getHexInt).let(::AvBytes)

	override fun visitInt(ctx:IntContext):AvInt=
		ctx.Int()?.let(::getInt)
		?: getHexInt(ctx.HexInt())

	fun getInt(i:TerminalNode):AvDecimal=
		AvDecimal(i.text.toInt())

	fun getHexInt(i:TerminalNode):AvHexInt=
		AvHexInt(i.text.drop(1).toInt(16))

	override fun visitString(ctx:StringContext):AvString
	{
		val contents=ctx.string_content().map(::visitString_content)
		return AvString(contents)
	}

	override fun visitString_content(ctx:String_contentContext):AvStringContent=
		ctx.exp()?.let(::visitExp)
		?: AvText(ctx.TEXT().text)
}