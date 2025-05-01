package environment

import ast.*
import environment.CompressionType.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass
import kotlin.streams.asSequence

enum class CompressionType
{
	Uncompressed,
	Compact,
	Minified;

	fun <T> switch(
		onUncompressed:T,
		onCompact:T,
		onMinified:T=onCompact
	):T=
		when(this)
		{
			Uncompressed->onUncompressed
			Compact->onCompact
			Minified->onMinified
		}

	val isSet get()=this!=Uncompressed
}

class AvPrinter
{
	companion object
	{

		private infix fun AvNode.omits(other:AvNode):Boolean=
			when(this)
			{
				//AvVarExp[I] AvRefExp AvBorrowExp AvList AvBytes AvInt
				//AvDecimal AvHexInt AvFloat AvTrue AvFalse AvNil
				//AvString AvMap AvPlainMapEntry AvDeclEntry EBind EBorrow
				is AvVarExp->when(other)
				{
					is AvVarExp,is AvList,is AvBytes,is AvHexInt,is AvString,is AvMap,is EBind,
					EBorrow.Overlay,EBorrow.Push->true

					is AvRefExp->name omits other.exp
					is AvBorrowExp->name omits other.left
					else->false
				}

				is AvRefExp->when(other)
				{
					is AvVarExp->true
					else->false
				}

				is AvList->true
				is AvString->true
				is AvMap->true
				is AvBytes->true
				else->false
			}

		@OptIn(ExperimentalStdlibApi::class)
		fun stringify(node:AvNode,compressionType:CompressionType=Compact):String=
			node.run {
				return@run when(this)
				{
					is AvChunk->stringify(inner,compressionType)

					is AvVarExp->"'"+stringify(name,compressionType)
					is AvRefExp->"${stringify(exp,compressionType)}.${stringify(atom,compressionType)}"
					is AvBorrowExp->
						"${stringify(left)} ${stringify(borrow,compressionType)} ${stringify(right,compressionType)}"

					is AvBytes->
						ints.parallelStream()
							.map {stringify(it,compressionType)}
							.asSequence()
							.joinToString(" ","(",")")

					is AvDecimal->"$value"
					is AvHexInt->value.toString(16)
					is AvFloat->"$value"
					is AvTrue->"true"
					is AvFalse->"false"
					is AvNil->"null"
					is AvString->
						contents.parallelStream()
							.map {stringify(it,compressionType)}
							.asSequence()
							.joinToString("","\"","\"")

					is AvText->value
					is AvConstantString->
					{
						val isId=Regex("[a-zA-Z_0-9+/%|&^<=>*!?\u0391-\u03A9\u03B1-\u03C9-]+").matches(text)
						if(compressionType.isSet&&isId)
							text
						else
							"\"$text\""
					}

					is AvId->if(compressionType.isSet) value else "\"$value\""
					is AvList->
					{
						when(compressionType)
						{
							Uncompressed->entries.parallelStream()
								.map {stringify(it,compressionType)}
								.asSequence()
								.joinToString(", ","[","]")

							Compact->entries.parallelStream()
								.map {stringify(it,compressionType)}
								.asSequence()
								.joinToString(" ")

							Minified->
							{
								var lastEntry:AvListEntry?=null
								val builder=StringBuilder()
								for((i:Int,e:AvListEntry) in entries.withIndex())
								{
									if(i==0)
									{
										lastEntry=e
										builder.append(stringify(lastEntry,compressionType))
									}
									else
									{
										builder.append(stringify(lastEntry!!,compressionType))
										if(!lastEntry.omits(e))
											builder.append(' ').also {
												println("${lastEntry!!::class} !omits ${e::class}")
											}
										builder.append(stringify(e,compressionType))
										lastEntry=e
									}
								}
								builder.toString()
							}
						}
						entries.parallelStream()
							.map {stringify(it,compressionType)}
							.asSequence()
							.joinToString(
								if(compressionType.isSet) " " else ", ",
								"[",
								"]"
							)
					}

					is AvMap->entries.parallelStream()
						.map {stringify(it,compressionType)}
						.asSequence()
						.joinToString(
							if(compressionType.isSet) " " else ", ",
							"{",
							"}"
						)

					is AvDeclEntry->
						"${stringify(name,compressionType)} $bind ${stringify(value,compressionType)}"

					is AvPlainMapEntry->
					{
						if(compressionType.isSet)
							"${stringify(key)} ${stringify(value)}"
						else
							"${key.stringifyAsMapKey()}: ${stringify(value,compressionType)}"
					}

					is EBind->text
					is EBorrow->text
					else->TODO(this::class.toString())
				}
			}

		private fun AvAtom.stringifyAsMapKey():String=
			when(this)
			{
				is AvStringLike->stringify(this,Uncompressed)
				else->"\"${stringify(this,Uncompressed)}\""
			}
	}
}