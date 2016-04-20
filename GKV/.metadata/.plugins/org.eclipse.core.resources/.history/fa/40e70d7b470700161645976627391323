package edu.asu.msse.gkv;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class GKVCompiler {
	public static void main(String[] args) throws IOException {
		ANTLRFileStream antlrFileStream = new ANTLRFileStream("/home/gowtham/program.gkv");
		//CharStream charStream = new ANTLRStringStream(antlrFileStream); 
		
		GKVLexer gkvLexer = new GKVLexer(antlrFileStream);
		TokenStream tokenStream = new CommonTokenStream(gkvLexer);
		
		GKVParser gkvParser = new GKVParser(tokenStream);
	    
		ParseTree programParseTree = gkvParser.program();
	    System.out.println(programParseTree.toStringTree(gkvParser));
		 
		 ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
		 parseTreeWalker.walk(new GKVWalker(), programParseTree);
		 
		System.out.println("done");
	}
}
