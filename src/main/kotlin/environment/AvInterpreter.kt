package environment

import ast.*
import ast.AvBool.Companion.and
import ast.AvBool.Companion.av
import ast.AvBool.Companion.imply
import ast.AvBool.Companion.nand
import ast.AvBool.Companion.nor
import ast.AvBool.Companion.or
import ast.AvBool.Companion.xnor
import ast.AvFloat.Companion.av
import ast.AvInt.Companion.av
import ast.AvPlainMapEntry.Companion.av
import ast.AvString.Companion.av
import ast.AvStringLike.Companion.extract
import ast.EBorrow.*
import environment.AvPrinter.Companion.stringify

private val String.text:AvText get()=AvText(this)

class AvInterpreter
{
	val AvChunk.eval:AvChunk get()=AvChunk(map.eval)

	private val AvMap.eval:AvMap get()=eval(GlobalScope)

	infix fun AvMap.eval(scope:Scope):AvMap
	{
		val outer=this
		return AvMap(parent=scope).apply {
			bindings+=outer.bindings
			outer.entries.asSequence()
				.forEach {
					val res=it eval this
					if(res is AvPlainMapEntry) addEntry(res)
					else if(res is AvDeclEntry)
					{
						val name=res.name eval this
						if(res.bind==EBind.Persistent)
							addEntry(AvPlainMapEntry(name,res.value))
						this[name.text]=res.value
					}
				}
		}
	}

	infix fun AvMapEntry.eval(scope:Scope):AvMapEntry=
		when(this)
		{
			is AvPlainMapEntry->eval(scope)
			is AvDeclEntry->eval(scope)
		}

	infix fun AvDeclEntry.eval(scope:Scope):AvDeclEntry
	{
		val name=name eval scope
		val value=value eval scope
		return AvDeclEntry(name,value,bind).also {
			when(scope)
			{
				is AvList->scope.props[name]=value
				is AvMap->scope.props[name]=value
				GlobalScope->
				{
				}
			}
			scope.bindings[name.text]=value
		}
	}

	infix fun AvExp.eval(scope:Scope):AvExp=
		when(this)
		{
			is AvAtom->eval(scope)
			is AvBorrowExp->eval(scope)
			is AvList->eval(scope)
			is AvMap->eval(scope)
			is AvRefExp->eval(scope)
			is AvVarExp->eval(scope)
		}

	infix fun AvList.eval(scope:Scope):AvList
	{
		val newList=mutableListOf<AvListEntry>()
		val avList=AvList(newList,scope)

		entries.asSequence()
			.map {it eval avList}
			.forEach {
				if(it is AvExp) newList+=it
				else if(it is AvDeclEntry)
				{
					if(it.bind==EBind.Persistent)
						newList+=it.value
					avList.props[it.name]=it.value
				}
			}

		return avList
	}

	infix fun AvPlainMapEntry.eval(scope:Scope):AvPlainMapEntry=
		AvPlainMapEntry(key,value eval scope)

	infix fun AvVarExp.eval(scope:Scope):AvExp=
		(name eval scope).let {
			scope[it.text]?.let {e-> e eval scope}
			?: error("Binding not found: $it")
		}


	infix fun AvRefExp.eval(scope:Scope):AvExp
	{
		val exp1:AvExp=exp eval scope
		return if(exp1 is Scope)
		{
			when(val atom1=atom eval exp1)
			{
				is AvConstantString->
					exp1[atom1.text]
					?: error(
						"Property not found: "+stringify(atom1)+
						"; me: "+stringify(exp1)
					)

				else->TODO("Getting ${exp1::class}.$atom")
			}
		}
		else TODO("Getting ${exp1::class}.$atom")
	}

	infix fun AvAtom.eval(scope:Scope):AvAtom=
		when(this)
		{
			is AvStringLike->eval(scope)
			else->this
		}

	infix fun AvString.eval(scope:Scope):AvConstantString=
		contents.fold("") {acc:String,entry:AvStringContent->
			when(entry)
			{
				is AvText->acc+entry.value
				is AvConstantString->acc+entry.text

				is AvExp->acc+when(val exp=entry eval scope)
				{
					is AvString->exp.contents.joinToString("") {stringify(it eval scope)}
					is AvConstantString->exp.text
					else->stringify(exp)
				}
			}
		}.av.also {
			println("In: $this")
			println("Out: $it")
			val isId=Regex("[a-zA-Z_0-9+/%|&^<=>*!?\u0391-\u03A9\u03B1-\u03C9-]+").matches(it.text)
			if(isId) println("\tAnd also: ${AvId(it.text)}")
		}

	infix fun AvStringContent.eval(scope:Scope):AvText=
		when(this)
		{
			is AvText->this
			is AvExp->stringify(eval(scope)).text
		}

	infix fun AvListEntry.eval(scope:Scope):AvListEntry=
		when(this)
		{
			is AvDeclEntry->eval(scope)
			is AvExp->eval(scope)
		}

	/**
	 * I assume that lists are fully evaluated
	 * */
	private fun EBorrow.joinLists(l:AvList,r:AvList,scope:Scope):AvList=
		when(this)
		{
			Overlay->AvList(l.entries.toSet()+r.entries)
			Unite->l+r
			Default->AvList(defaultWithPredicate(l.entries,r.entries,AvListEntry::equals))
			Intersect->l.entries
				.toSet()
				.intersect(r.entries.toSet())
				.let(::AvList)

			Differ->AvList(l.entries.toSet()-r.entries.toSet())

			Either->
			{
				val listDifferL=Differ.joinLists(l,r,scope)
				val listDifferR=Differ.joinLists(r,l,scope)
				Unite.joinLists(listDifferL,listDifferR,scope)
			}

			Push->error("Shall not be reached")
		}

	/**
	 * I assume that maps are fully evaluated
	 * */
	private fun EBorrow.joinMaps(l:AvMap,r:AvMap,scope:Scope):AvMap=
		when(this)
		{
			Overlay->
			{
				val lMap=l.getPlainEntries()
					.associate {(k,v)-> k to v}
					.toMutableMap()

				r.getPlainEntries()
					.associate {(k,v)-> k to v}
					.forEach {(k,v)-> lMap[k]=v}

				lMap.entries.map {(k,v)->
					AvPlainMapEntry(k,v)
				}.let(::AvMap)
			}

			Unite->AvMap(l.entries+r.entries)

			Default->
			{
				val lMap=l.kt.toMutableMap()
				r.kt.forEach {(k,v)-> if(k !in lMap) lMap[k]=v}
				lMap.entries.map {it.av}.let(::AvMap)
			}


			Intersect->intersectWithPredicate(
				l.getPlainEntries(),
				r.getPlainEntries(),
				AvMap::keysAreSame
			).let(::AvMap)

			Differ->differWithPredicate(
				l.getPlainEntries(),
				r.getPlainEntries(),
				AvMap::keysAreSame
			).let(::AvMap)

			Either->
			{
				val mapDifferL=Differ.joinMaps(l,r,scope)
				val mapDifferR=Differ.joinMaps(r,l,scope)
				Unite.joinMaps(mapDifferL,mapDifferR,scope)
			}

			Push->error("Shall not be reached")
		}


	private fun <T> defaultWithPredicate(
		left:Iterable<T>,
		right:Iterable<T>,
		predicate:(T,T)->Boolean
	):(List<T>)=
		left.map {l->
			right.find {predicate(l,it)} ?: l
		}

	private fun <T> intersectWithPredicate(
		left:Iterable<T>,
		right:Iterable<T>,
		predicate:(T,T)->Boolean
	):(Set<T>)=
		// Проверяем элементы левой коллекции
		left.toSet().filter {l->
			// Если элемент из левой коллекции есть и в правой коллекции
			right.toSet().any {predicate(l,it)}
		}.toSet()

	private fun <T> differWithPredicate(
		left:Iterable<T>,
		right:Iterable<T>,
		predicate:(T,T)->Boolean
	):(Set<T>)=
		// Проверяем элементы левой коллекции
		left.toSet().filter {l->
			// Если элемент из левой коллекции не удовлетворяет предикату ни для одного элемента из правой коллекции
			right.toSet().none {predicate(l,it)}
		}.toSet()

	infix fun AvBorrowExp.eval(scope:Scope):AvExp
	{
		val l=left.eval(scope)
		val r=right.eval(scope)
		return when(borrow)
		{
			Overlay->when
			{
				l is AvBool&&r is AvBool->l xnor r
				l is AvNil->r
				l is AvList&&r is AvList->Overlay.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Overlay.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Overlay.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Overlay.joinMaps(l,r,scope)

				else->l
			}

			//int float string bool nil bytes array map
			Unite->when
			{
				l is AvInt->when(r)
				{
					is AvInt->(l.value+r.value).av               // int
					is AvFloat->(l.value+r.value).av             // float
					is AvStringLike->"${l.value}${r.extract}".av // string
					is AvBool->(l.value+r.asInt).av              // int
					else->l
				}

				l is AvFloat->when(r)
				{
					is AvInt->(l.value+r.value).av               // float
					is AvFloat->(l.value+r.value).av             // float
					is AvStringLike->"${l.value}${r.extract}".av // string
					is AvBool->(l.value+r.asInt).av              // float
					else->l
				}

				l is AvStringLike->when(r)
				{
					is AvInt->(l.extract+r.value).av          // string
					is AvFloat->(l.extract+r.value).av        // string
					is AvStringLike->(l.extract+r.extract).av // string
					is AvBool->(l.extract+r.asInt).av         // string
					else->l
				}

				l is AvBool->when(r)
				{
					is AvInt->(l.asInt+r.value).av       // string
					is AvFloat->(l.asInt+r.value).av     // string
					is AvStringLike->"$l${r.extract}".av // string
					is AvBool->l nor r                   // string
					else->l
				}

				l is AvNil->r
				l is AvBytes&&r is AvBytes->AvBytes(l.ints+r.ints)
				l is AvList&&r is AvList->Unite.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Unite.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Unite.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Unite.joinMaps(l,r,scope)

				else->l
			}

			Default->when
			{
				l is AvBool&&r is AvBool->l or r
				l is AvNil->r
				l is AvList&&r is AvList->Default.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Default.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Default.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Default.joinMaps(l,r,scope)
				else->l
			}

			Intersect->when
			{
				l is AvBool&&r is AvBool->l and r
				l is AvNil->r
				l is AvList&&r is AvList->Intersect.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Intersect.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Intersect.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Intersect.joinMaps(l,r,scope)
				else->l
			}

			Differ->when
			{
				l is AvInt->when(r)
				{
					is AvInt->(l.value-r.value).av   // int
					is AvFloat->(l.value-r.value).av // float
					is AvBool->(l.value-r.asInt).av  // int
					else->l
				}

				l is AvFloat->when(r)
				{
					is AvInt->(l.value-r.value).av   // float
					is AvFloat->(l.value-r.value).av // float
					is AvBool->(l.value-r.asInt).av  // float
					else->l
				}

				l is AvBool->when(r)
				{
					is AvInt->(l.asInt-r.value).av   // int
					is AvFloat->(l.asInt-r.value).av // float
					is AvBool->l nand r              // bool
					else->l
				}

				l is AvNil->r

				l is AvList&&r is AvList->Differ.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Differ.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Differ.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Differ.joinMaps(l,r,scope)

				else->l
			}

			Either->when
			{
				l is AvBool&&r is AvBool->(l.value xor r.value).av
				l is AvNil->r

				l is AvList&&r is AvList->Either.joinLists(l,r,scope)
				l is AvList&&r is AvMap->Either.joinLists(l,r.asList,scope)
				l is AvMap&&r is AvList->Either.joinMaps(l,r.asMap,scope)
				l is AvMap&&r is AvMap->Either.joinMaps(l,r,scope)

				else->l
			}

			Push->when
			{
				l is AvBool&&r is AvBool->l imply r
				l is AvNil->r
				l is AvList->AvList(l.entries+r)
				else->l
			}
		}
	}

	infix fun AvStringLike.eval(scope:Scope):AvConstantString=
		when(this)
		{
			is AvId->AvConstantString(this.value)
			is AvString->eval(scope)
			is AvConstantString->this
		}
}