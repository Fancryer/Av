package org.fancryer;

import org.fancryer.gen.AvLexer;
import org.fancryer.gen.AvParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		var lexer=new AvLexer(CharStreams.fromFileName("src/main/resources/yt_response.av"));
		var parser=new AvParser(new CommonTokenStream(lexer));
		var chunk=parser.chunk();
		System.out.println(chunk.toStringTree(parser));

	}
}
