package ast.builders

import ast.*
import ast.AvString.Companion.av

class AvDeclBuilder(private val name: String) {
	private var value: AvExp = AvNil
	private var bind: EBind = EBind.Temporary

	infix fun value(value: AvExp) {
		this.value = value
	}

	infix fun bind(bind: EBind) {
		this.bind = bind
	}

	private fun build(): AvDeclEntry = AvDeclEntry(name.av, value, bind)

	companion object {
		fun avdecl(name: String, init: AvDeclBuilder.() -> Unit): AvDeclEntry =
			AvDeclBuilder(name).apply(init).build()
	}
}