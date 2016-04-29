package edu.asu.msse.gkv;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class GKVCompiler {
	public static void main(String[] args) throws IOException {
		ANTLRFileStream antlrFileStream = new ANTLRFileStream(Constants.HIGH_LEVEL_LANGUAGE_FILE_PATH + args[0]);
		
		GKVLexer gkvLexer = new GKVLexer(antlrFileStream);
		TokenStream tokenStream = new CommonTokenStream(gkvLexer);
		
		GKVParser gkvParser = new GKVParser(tokenStream);
	    
		ParseTree programParseTree = gkvParser.program();
	    System.out.println(programParseTree.toStringTree(gkvParser));
		 
		 ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
		 parseTreeWalker.walk(new GKVWalker(args[0]), programParseTree);
		 
		System.out.println("done");
	}
}
