package ast

import org.antlr.v4.runtime.tree.TerminalNode
import org.antlr.v4.runtime.tree.Tree

class ParseTreeSourcifier:Sourcifier<Tree>
{
	override fun sourcify(tree:Tree?):String?=sourcify(tree,StringBuilder())

	override fun sourcify(trees:List<Tree?>):(List<String?>)=trees.map(::sourcify)

	override fun sourcify(tree:Tree?,builder:StringBuilder):String?
	{
		if(tree==null) return null
		val childCount=tree.childCount;
		(0..<childCount).map(tree::getChild).forEach {
			if(it is TerminalNode)
				builder.append(it).append(" ")
			sourcify(it,builder)
		}
		return builder.toString().trim().replace("<EOF>","");
	}
}
