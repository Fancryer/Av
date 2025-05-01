package environment

import ast.*
import kotlin.math.max

class AvFormatter:AutoCloseable
{
	private var indent=0
	private var builder=StringBuilder()

	fun append(text:CharSequence)
	{
		repeat(max(indent,0)) {builder.append('\t')}
		builder.append(text)
	}

	fun append(char:Char)=append("$char")

	@OptIn(ExperimentalStdlibApi::class)
	fun format(node:AvNode):String
	{
		node.run {
			when(this)
			{
				is AvChunk->format(inner)

				is AvVarExp->
				{
					append('\'')
					format(name)
				}

				is AvRefExp->
				{
					format(exp)
					builder.append('.')
					format(atom)
				}

				is AvBorrowExp->
				{
					format(left)
					builder.append(' ')
					format(borrow)
					builder.append(' ')
					format(right)
				}

				is AvBytes->ints.joinToString(" ","(",")") {format(it)}.let(::append)
				is AvDecimal->append("$value")
				is AvHexInt->append(value.toInt().toHexString())
				is AvFloat->append("$value") //"$value"
				is AvTrue->builder.append(true)
				is AvFalse->builder.append(false)
				is AvNil->
				{
					builder.append("null")
				} //"null"
				is AvString->
				{
					append('"')
					contents.forEach {
						format(it)
					}
					builder.append('"')
				}

				is AvText->builder.append(value)
				is AvConstantString->append("\"${text}\"")
				is AvId->append(value)

				is AvList->
				{
					append('[')
					builder.append('\n')
					++indent
					entries.forEach {
						format(it)
						append('\n')
					}
					--indent
					append(']')
				}

				is AvMap->
				{
					append('{')
					builder.append('\n')
					++indent
					entries.forEach {
						format(it)
						append('\n')
					}
					--indent
					append('}')
				}

				is AvDeclEntry->TODO(builder.toString()) //"${format(name)} $bind ${format(value)}"
				is AvPlainMapEntry->
				{
					format(key)
					builder.append(' ')
					format(value)
				}

				is EBind->TODO(builder.toString()) //text
				is EBorrow->TODO(builder.toString()) //text
				else->TODO("${node::class}")
			}
		}
		return builder.toString()
	}

	override fun close()
	{
		indent=0
		builder.clear()
	}
}