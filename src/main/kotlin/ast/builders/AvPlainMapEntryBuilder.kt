package ast.builders

import ast.*
import ast.AvString.Companion.av

class AvPlainMapEntryBuilder private constructor(private val name: AvAtom) {
	private var value: AvExp = AvNil

	infix fun value(value: AvExp) {
		this.value = value
	}

	operator fun AvExp.unaryPlus() {
		value = this
	}

	private fun build(): AvPlainMapEntry = AvPlainMapEntry(name, value)

	companion object {
		fun mapEntry(name: String, init: AvPlainMapEntryBuilder.() -> Unit): AvPlainMapEntry =
			AvPlainMapEntryBuilder(name.av).apply(init).build()

		fun mapEntry(name: String, exp: AvExp): AvPlainMapEntry = AvPlainMapEntry(name.av, exp)

		fun mapEntry(name: AvAtom, init: AvPlainMapEntryBuilder.() -> Unit): AvPlainMapEntry =
			AvPlainMapEntryBuilder(name).apply(init).build()

		fun mapEntry(name: AvAtom, exp: AvExp): AvPlainMapEntry = AvPlainMapEntry(name, exp)
	}
}