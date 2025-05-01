package ast.builders

import ast.*
import ast.AvString.Companion.av
import ast.builders.AvPlainMapEntryBuilder.Companion.mapEntry

class AvListBuilder(private val parent: Scope? = GlobalScope) {
	var entries = mutableListOf<AvListEntry>()

	infix fun AvListEntry.entry(entry: AvListEntry)
	{
		entries+=entry
	}

	operator fun AvListEntry.unaryPlus() {
		entries += this
	}

	val AvListEntry.entry get() = entry(this)

	private fun build(): AvList = AvList(entries, parent)

	companion object {
		fun list(parent: Scope? = GlobalScope, init: AvListBuilder.() -> Unit): AvList =
			AvListBuilder(parent).apply(init).build()
	}
}