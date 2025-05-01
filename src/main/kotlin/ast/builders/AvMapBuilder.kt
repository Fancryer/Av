package ast.builders

import ast.*
import ast.AvString.Companion.av
import ast.AvTrue.list
import ast.builders.AvPlainMapEntryBuilder.Companion.mapEntry

class AvMapBuilder(private val parent:Scope?=GlobalScope)
{
	var entries=mutableListOf<AvMapEntry>()

	// Для простого значения (AvExp)
	infix fun String.entry(value:AvExp)=this.av entry value

	// Для блока инициализации
	infix fun String.map(init:AvPlainMapEntryBuilder.()->Unit)=entry(this.av,init)

	infix fun String.list(init:AvListBuilder.()->Unit):AvPlainMapEntry=this.av list init

	infix fun AvAtom.list(init:AvListBuilder.()->Unit):AvPlainMapEntry=this entry (AvListBuilder.list {init()})

	// Для значения AvExp с использованием AvAtom
	infix fun AvAtom.entry(value:AvExp):AvPlainMapEntry=AvPlainMapEntry(this,value).also {entries+=it}

	// Для блока инициализации с использованием AvAtom
	infix fun AvAtom.entry(init:AvPlainMapEntryBuilder.()->Unit):AvPlainMapEntry=mapEntry(this,init)

	// Для пар ключ/значение с AvAtom и блоком инициализации
	fun entry(key:AvAtom,init:AvPlainMapEntryBuilder.()->Unit):AvMapEntry=
		mapEntry(key,init).also {entries+=it}

	// Для пар ключ/значение с String и блоком инициализации
	fun entry(key:String,init:AvPlainMapEntryBuilder.()->Unit):AvMapEntry=
		entry(key.av,init)

	// Для простого ключа и значения (AvExp)
	infix fun AvExp.entry(key:String)=entry(key.av)
	infix fun AvExp.entry(key:AvAtom)=AvPlainMapEntry(key,this).also {entries+=it}

	fun declEntry(name:AvStringLike,value:AvExp,bind:EBind=EBind.Temporary)=
		AvDeclEntry(name,value,bind).also {entries+=it}

	operator fun AvMapEntry.unaryPlus()
	{
		entries+=this
	}

	private fun build():AvMap=AvMap(entries,parent)

	companion object
	{
		fun map(parent:Scope?=GlobalScope,init:AvMapBuilder.()->Unit):AvMap=
			AvMapBuilder(parent).apply(init).build()
	}
}
