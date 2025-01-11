package ast

interface Sourcifier<T>
{
	fun sourcify(tree:T?):String?

	fun sourcify(trees:List<T?>):List<String?>?

	fun sourcify(tree:T?,builder:StringBuilder):String?
}