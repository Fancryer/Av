import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ParseTreeSourcifier implements Sourcifier<Tree>
{
	@Override
	public String sourcify(Tree tree)
	{
		return sourcify(tree,new StringBuilder());
	}

	@Override
	public List<String> sourcify(List<Tree> trees)
	{
		return trees.stream().map(this::sourcify).toList();
	}

	@Override
	public String sourcify(Tree tree,StringBuilder builder)
	{
		if(tree==null) return "";
		int childCount=tree.getChildCount();
		Consumer<Tree> childToSource=child->
		{
			if(child instanceof TerminalNode parserRuleContext)
				builder.append(parserRuleContext).append(" ");
			sourcify(child,builder);
		};
		IntStream.range(0,childCount)
		         .mapToObj(tree::getChild)
		         .forEach(childToSource);
		return builder.toString().trim().replace("<EOF>","");
	}
}
