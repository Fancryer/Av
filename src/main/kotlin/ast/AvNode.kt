package ast

import ast.AvInt.Companion.av
import ast.AvString.Companion.av
import ast.AvStringLike.Companion.extract
import environment.AvPrinter
import environment.AvPrinter.Companion.stringify
import java.util.*

interface AvNode

data class AvChunk(val map:AvMap=AvMap()):AvNode
{
	override fun toString():String="(AvChunk $map)"
}

sealed interface AvExp:AvListEntry,AvStringContent
{
	val props:MutableMap<AvAtom,AvExp>

	operator fun get(prop:AvAtom,scope:Scope):(AvExp)=
		props[prop]
		?: error("Property not found: $prop; my props: $props")

	val AvExp.list get()=AvList(listOf(this))
}

data class AvVarExp(val name:AvStringLike):AvExp
{
	override val props=mutableMapOf<AvAtom,AvExp>()

	override fun toString():String="(AvVarExp $name)"
}

data class AvRefExp(val exp:AvExp,val atom:AvAtom):AvExp
{
	override val props=mutableMapOf<AvAtom,AvExp>()
	override fun toString():String="(AvVarExp $exp $atom)"
}

data class AvBorrowExp(val left:AvExp,val borrow:EBorrow,val right:AvExp):AvExp
{
	override val props:MutableMap<AvAtom,AvExp> get()=mutableMapOf()
	override fun toString():String="(AvBorrowExp $left $borrow $right)"
}

sealed interface AvAtom:AvExp

class AvList(
	val entries:(List<AvListEntry>)=emptyList(),
	parent:Scope?=GlobalScope
):AvExp,Scope(parent)
{
	constructor(entries:Sequence<AvListEntry>,parent:Scope?=GlobalScope):this(entries.toList(),parent)
	constructor(entries:Iterable<AvListEntry>,parent:Scope?=GlobalScope):this(entries.toList(),parent)
	constructor(vararg entries:AvListEntry,parent:Scope?=GlobalScope):this(entries.toList(),parent)

	override val props:(MutableMap<AvAtom,AvExp>)=
		bindings.mapKeys {it.key.av}
			.mapValues {it.value}
			.toMutableMap()

	init
	{
		props["size".av]=getPlainEntries().size.av
	}

	operator fun plus(other:AvList):AvList=
		AvList(entries+other.entries)

	val asMap:AvMap by lazy {
		entries.asSequence()
			.mapIndexed {index,it->
				when(it)
				{
					is AvDeclEntry->it
					is AvExp->AvPlainMapEntry(index.av,it)
				}
			}.let(::AvMap)
	}


	fun getPlainEntries()=
		entries.asSequence()
			.filterIsInstance<AvExp>()
			.toList()

	override fun get(prop:AvAtom,scope:Scope):AvExp=
		when(prop)
		{
			is AvStringLike->
				if(prop.extract=="size") getPlainEntries().size.av
				else if(prop in props) props[prop]!!
				else error("Unexpected property: $prop")

			is AvDecimal->getPlainEntries()[prop.value]

			else->if(prop in props) props[prop]!!
			else error("Unexpected property: $prop")
		}.also {props[prop]=it}

	override fun toString():String="(AvList $entries)"
}

sealed interface AvListEntry:AvNode

data class AvBytes(val ints:(List<AvHexInt>)=emptyList()):AvAtom
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()
}

sealed class AvInt:AvAtom
{
	abstract val value:Int

	companion object
	{
		val Int.av get()=AvDecimal(this)
	}
}

data class AvDecimal(override val value:Int):AvInt()
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()
	override fun toString():String="(AvDecimal $value)"
}

data class AvHexInt(override val value:Int):AvInt()
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()

	@OptIn(ExperimentalStdlibApi::class)
	override fun toString():String="(AvHexInt ${value.toHexString()})"
}


data class AvFloat(val value:Float):AvAtom
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()
	override fun toString():String="(AvFloat $value)"

	companion object
	{
		val Float.av:AvFloat get()=AvFloat(this)
	}
}

sealed interface AvBool:AvAtom
{
	val asInt:Int
	val value:Boolean

	companion object
	{
		val Boolean.av get()=if(this) AvTrue else AvFalse

		infix fun AvBool.and(other:AvBool):AvBool=(value&&other.value).av
		infix fun AvBool.or(other:AvBool):AvBool=(value||other.value).av
		infix fun AvBool.xnor(other:AvBool):AvBool=(value==other.value).av
		infix fun AvBool.nor(other:AvBool):AvBool=(!(value||other.value)).av
		infix fun AvBool.nand(other:AvBool):AvBool=(!(value&&other.value)).av
		infix fun AvBool.imply(other:AvBool):AvBool=(!value||other.value).av
	}
}

data object AvTrue:AvBool
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf(
		"not".av to AvFalse
	)

	override val asInt:Int=1
	override val value:Boolean=true
	override fun toString():String="AvTrue"
}

data object AvFalse:AvBool
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf(
		"not".av to AvTrue
	)
	override val asInt:Int=0
	override val value:Boolean=false
	override fun toString():String="AvFalse"
}

data object AvNil:AvAtom
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()
	override fun toString():String="AvNil"
}

sealed interface AvStringLike:AvAtom
{
	companion object
	{
		val AvStringLike.extract
			get()=when(this)
			{
				is AvId->value
				is AvString->join
				is AvConstantString->text
			}

		val AvStringLike.string
			get()=when(this)
			{
				is AvId->AvString(this)
				is AvString,is AvConstantString->this
			}
	}
}

data class AvConstantString(val text:String):AvStringLike
{
	override val props:MutableMap<AvAtom,AvExp>
		get()=mutableMapOf(
			"length".av to AvDecimal(text.length),
			"chars".av to text.toList()
				.map {"$it"}
				.map {it.av}
				.let(::AvList)
		)

	override fun toString():String="(AvConstantString '$text')"
}

data class AvString(val contents:(List<AvStringContent>)=emptyList()):AvStringLike
{
	constructor(id:AvId):this(listOf(AvText(id.value)))
	constructor(text:AvText):this(listOf(text))
	constructor(string:String):this(string.av)
	constructor(string:AvConstantString):this(AvText(string.text))
	constructor(string:AvString):this(string.contents)

	override val props:(MutableMap<AvAtom,AvExp>)
		get()=mutableMapOf(
			"length".av to AvDecimal(join.length),
			"chars".av to join.toList()
				.map {"$it"}
				.map {it.av}
				.let(::AvList)
		)

	val join:String
		get()=contents.joinToString("") {
			when(it)
			{
				is AvText->it.value
				is AvExp->it.toString()
			}
		}

	override fun toString():String="(AvString '$join')"

	companion object
	{
		val String.av get()=AvConstantString(this)
	}
}

sealed interface AvStringContent:AvNode
data class AvText(val value:String):AvStringContent
{
	override fun toString():String="(AvText '$value')"
}

data class AvId(val value:String):AvStringLike
{
	override val props:(MutableMap<AvAtom,AvExp>)=mutableMapOf()

	override fun toString():String="(AvId $value)"

	override fun equals(other:Any?):Boolean=
		this===other
		||other is AvId&&value==other.value

	override fun hashCode():Int=value.hashCode()
}

class AvMap(
	val entries:(MutableList<AvMapEntry>)=mutableListOf(),
	parent:Scope?=GlobalScope
):AvExp,Scope(parent)
{
	val kt:Map<AvAtom,AvExp>
		get()=getPlainEntries().associate {it.key to it.value}

	constructor(entries:Sequence<AvMapEntry>,parent:Scope?=GlobalScope):this(entries.toList(),parent)
	constructor(entries:Iterable<AvMapEntry>,parent:Scope?=GlobalScope):this(entries.toMutableList(),parent)
	constructor(vararg entries:AvMapEntry,parent:Scope?=GlobalScope):this(entries.toList(),parent)

	override val props:(MutableMap<AvAtom,AvExp>)=
		bindings.mapKeys {it.key.av}
			.mapValues {it.value}
			.toMutableMap()

	init
	{
		props["entries".av]=mapPlainEntries {AvList(it.key,it.value)}.let(::AvList)
		props["keys".av]=mapPlainEntries {it.key}.let(::AvList)
		props["values".av]=mapPlainEntries {it.value}.let(::AvList)
	}

	val asList:AvList by lazy {
		entries.asSequence()
			.map {
				when(it)
				{
					is AvDeclEntry->it
					is AvPlainMapEntry->AvList(listOf(it.key,it.value))
				}
			}.let(::AvList)
	}

	fun getPlainEntries():(List<AvPlainMapEntry>)=
		entries.asSequence()
			.filterIsInstance<AvPlainMapEntry>()
			.toList()

	fun <R> mapPlainEntries(transform:(AvPlainMapEntry)->R):(Sequence<R>)=
		entries.asSequence()
			.filterIsInstance<AvPlainMapEntry>()
			.map(transform)

	// I assume that prop is fully evaluated

	override fun toString():String=buildString {
		append("(AvMap (props $props) (entries {")
		entries.forEach(::append)
		append("}))")
	}

	fun addEntry(entry:AvPlainMapEntry)
	{
		entries+=entry
		props["entries".av]=mapPlainEntries {AvList(it.key,it.value)}.let(::AvList)
		props["keys".av]=mapPlainEntries {it.key}.let(::AvList)
		props["values".av]=mapPlainEntries {it.value}.let(::AvList)
	}

	companion object
	{
		fun keysAreSame(left:AvPlainMapEntry,right:AvPlainMapEntry):Boolean=
			left.key==right.key
	}
}

sealed interface AvMapEntry:AvNode
data class AvPlainMapEntry(val key:AvAtom,val value:AvExp):AvMapEntry
{
	override fun toString():String="(AvPlainMapEntry $key $value)"

	override fun equals(other:Any?):Boolean
	{
		if(this===other) return true
		if(other !is AvPlainMapEntry) return false

		if(key!=other.key) return false
		if(value!=other.value) return false

		return true
	}

	override fun hashCode():Int=Objects.hash(key,value)

	companion object
	{
		val Map.Entry<AvAtom,AvExp>.av
			get()=AvPlainMapEntry(key,value)
	}
}

data class AvDeclEntry(
	val name:AvStringLike,
	val value:AvExp,
	val bind:EBind=EBind.Temporary
):AvMapEntry,AvListEntry
{
	override fun toString():String="(AvDeclEntry $name $bind $value)"

	override fun equals(other:Any?):Boolean
	{
		if(this===other) return true
		if(other !is AvDeclEntry) return false

		if(name!=other.name) return false
		if(value!=other.value) return false
		if(bind!=other.bind) return false

		return true
	}

	override fun hashCode():Int=Objects.hash(name,value,bind)
}

enum class EBind(val text:String):AvNode
{
	Temporary(":="),
	Persistent("::=");

	override fun toString():String=text
}

enum class EBorrow(val text:String):AvNode
{
	Overlay("~"),
	Unite("+"),
	Default("|"),
	Intersect("&"),
	Differ("-"),
	Either("^"),
	Push("@");

	override fun toString():String=text
}

sealed class Scope(val parent:Scope?=GlobalScope)
{
	val bindings=mutableMapOf<String,AvExp>()

	operator fun get(name:String):AvExp?
	{
		/*println(
			"Getting $name, me: ${
				when(val s=this)
				{
					is AvList->stringify(s)
					is AvMap->stringify(s)
					GlobalScope->"GlobalScope"
				}
			}, my bindings: ${
				stringify(bindings.map {AvPlainMapEntry(it.key.av,it.value)}
					.let(::AvMap))
			}"
		)
		val doIHaveIt=name in bindings
		println("Key: $name, do I have it: $doIHaveIt")
		if(!doIHaveIt)
			println("Okay, manually looking").also {
				bindings.forEach {(k,v)->
					println("Key $k")
					println("\tValue ${stringify(v)}")
					println("\t\tEvaluatedKey $name,")
					println("\t\t\tis this our key: ${k==name}")
				}
			}
		else
			println("I found it")*/
		return bindings[name] //trying to get in local scope
			   ?: parent?.get(name) //otherwise trying to get in parent
	}

	operator fun set(name:String,value:AvExp)
	{
		bindings[name]=value
	}
}

data object GlobalScope:Scope()