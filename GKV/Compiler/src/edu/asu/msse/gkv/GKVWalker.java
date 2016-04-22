package edu.asu.msse.gkv;

/**
 * Copyright 2016 Gowtham Ganesh Nayak,
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: 
 *
 * SER502 Principle of Programming Paradigms
 * see http://gowthamnayak.in/compilers
 *
 * @author Gowtham Ganesh Nayak mailto:gnayak2@asu.edu
 * @version April 2016
 */

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class GKVWalker extends GKVBaseListener {

	/*
	 * Using StringBuilder instead of StringBuffer because its fast but
	 * not thread safe. Thread Safety is not a concern in our program
	 * because we are not making use of threads in the program. This
	 * StringBuilder holds the intermediate language representation of 
	 * the High Level Language defined in the program.
	 */
	public StringBuilder stringBuilder;
	public final String WHITESPACE = " ";
	public final String SCOPESTART = "SCOPESTART";
	private final String NEWLINE = "\n";
	private final String FUNCEND = "FUNCEND";
	private final String SCOPEEND = "SCOPEEND";
	private final String RET = "RET";
	private final String SET = "SET";
	private final String PUSH = "PUSH";
	private final String MULT = "MULT";
	private final String DIV = "DIV";
	private final String ADD = "ADD";
	private final String SUB = "SUB";
	
	private TypeDictionary typeDictionary;
	private Map<String, String> typeMap;
	private final ComparisionDictionary comparisionDicitionary;
	private final Map<String, String> comparisionOperatorMap;
	
	/**
	 * Constructor for the walker.
	 */
	public GKVWalker() {
		this.stringBuilder = new StringBuilder();
		this.typeDictionary = new TypeDictionary();
		this.typeMap = this.typeDictionary.getTypeMap();
		this.comparisionDicitionary = new ComparisionDictionary();
		this.comparisionOperatorMap = this.comparisionDicitionary.getComparisionOperatorMap();
	}

	@Override 
	public void enterProgram(GKVParser.ProgramContext ctx) { }

	@Override 
	public void exitProgram(GKVParser.ProgramContext ctx) { 
		String intermediateProgram = stringBuilder.toString();
		System.out.print(" Inside Exit Program \n ");
		try {
			PrintWriter writer = new PrintWriter(Constants.INTERMEDIATE_LANGUAGE_FILE_NAME, Constants.ENCODING);
			writer.write(intermediateProgram);
			writer.close();
		} catch (Exception e){
			System.out.println(this.getClass().getSimpleName() + " Exception writing to file : " + Constants.INTERMEDIATE_LANGUAGE_FILE_NAME);
		}
	}

	@Override 
	public void enterSequenceOfStatements(GKVParser.SequenceOfStatementsContext ctx) { }
	
	@Override 
	public void exitSequenceOfStatements(GKVParser.SequenceOfStatementsContext ctx) { }
	
	@Override 
	public void enterSimpleStatement(GKVParser.SimpleStatementContext ctx) { }
	
	@Override 
	public void exitSimpleStatement(GKVParser.SimpleStatementContext ctx) { }
	
	@Override 
	public void enterCompoundStatement(GKVParser.CompoundStatementContext ctx) { }
	
	@Override 
	public void exitCompoundStatement(GKVParser.CompoundStatementContext ctx) { }

	@Override
	public void enterAssignmentStatement(GKVParser.AssignmentStatementContext ctx) { 
		/* Evaluate the expression and get the result 
		   The do the below with result */
	}
	
	@Override public void enterIfelseStatement(GKVParser.IfelseStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfelseStatement(GKVParser.IfelseStatementContext ctx) { }
	
	@Override
	public void exitAssignmentStatement(GKVParser.AssignmentStatementContext ctx) {
		stringBuilder.append(SET + WHITESPACE);
		stringBuilder.append(ctx.IDENTIFIER().getText().toUpperCase() + NEWLINE);
	}

	@Override 
	public void enterIfStatement(GKVParser.IfStatementContext ctx) { 
	}
	

	@Override
	public void exitIfStatement(GKVParser.IfStatementContext ctx) { 
		stringBuilder.append(SCOPEEND + NEWLINE);
		stringBuilder.append("JMP ELSEEND" + NEWLINE);
		stringBuilder.append("IFEND" + NEWLINE);
	}
	
	@Override public void enterElseStatement(GKVParser.ElseStatementContext ctx) { 
		stringBuilder.append("ELSESTART" + NEWLINE);
		stringBuilder.append(SCOPESTART + NEWLINE);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitElseStatement(GKVParser.ElseStatementContext ctx) {
		stringBuilder.append(SCOPEEND + NEWLINE);
		stringBuilder.append("ELSEEND" + NEWLINE);
	}
	
	@Override 
	public void enterLoop(GKVParser.LoopContext ctx) { }
	
	@Override
	public void exitLoop(GKVParser.LoopContext ctx) { }
	
	@Override 
	public void enterCondition(GKVParser.ConditionContext ctx) { 
	}
	
	@Override 
	public void exitCondition(GKVParser.ConditionContext ctx) { 
		stringBuilder.append("JMPIFFALSE" + WHITESPACE + "IFEND" + NEWLINE);
		stringBuilder.append("IFSTART" + NEWLINE);
		stringBuilder.append(SCOPESTART + NEWLINE);
	}
	
	@Override
	public void enterExpression(GKVParser.ExpressionContext ctx) { }
	
	@Override
	public void exitExpression(GKVParser.ExpressionContext ctx) {
		if (ctx.RELATIONOP(0) != null) {
			if (ctx.RELATIONOP(0).getText().equals("and")) {
				stringBuilder.append("LOGAND" + NEWLINE);
			} else if (ctx.RELATIONOP(0).getText().equals("or")) {
				stringBuilder.append("LOGOR" + NEWLINE);
			}
		}
	}
	
	@Override 
	public void enterRelation(GKVParser.RelationContext ctx) { }
	
	@Override 
	public void exitRelation(GKVParser.RelationContext ctx) {
		if (ctx.COMPK_KEYWORDS() != null) {
			stringBuilder.append(this.comparisionOperatorMap.get(ctx.COMPK_KEYWORDS().getText()) + NEWLINE);
		}
	}
	
	@Override 
	public void enterSimpleExpression(GKVParser.SimpleExpressionContext ctx) { }

	@Override 
	public void exitSimpleExpression(GKVParser.SimpleExpressionContext ctx) {
		if (ctx.ADDING_OPERATOR(0) != null) {
			if (ctx.ADDING_OPERATOR(0).getText().equals("+")) {
				stringBuilder.append(ADD + NEWLINE);
			} else if (ctx.ADDING_OPERATOR(0).getText().equals("-")) {
				stringBuilder.append(SUB + NEWLINE);
			}
		}
	}
	
	@Override 
	public void enterTerm(GKVParser.TermContext ctx) { }
	
	@Override 
	public void exitTerm(GKVParser.TermContext ctx) {
		if (ctx.MULTIPLYING_OPERATOR(0) != null) {
			if (ctx.MULTIPLYING_OPERATOR(0).getText().equals("*")) {
				stringBuilder.append(MULT + NEWLINE);
			} else if (ctx.MULTIPLYING_OPERATOR(0).getText().equals("/")) {
				stringBuilder.append(DIV + NEWLINE);
			}
		}
	}
	
	@Override 
	public void enterFactor(GKVParser.FactorContext ctx) { }
	
	@Override 
	public void exitFactor(GKVParser.FactorContext ctx) { 		
		if(ctx.INTEGER_LITERAL() != null) {
			stringBuilder.append(PUSH + WHITESPACE);
			stringBuilder.append(ctx.INTEGER_LITERAL().getText() + NEWLINE);
		} else if (ctx.DECIMAL_LITERAL() != null) {
			stringBuilder.append(PUSH + WHITESPACE);
			stringBuilder.append(ctx.DECIMAL_LITERAL().getText() + NEWLINE);
		} else if (ctx.IDENTIFIER() != null) {
			stringBuilder.append(PUSH + WHITESPACE);
			stringBuilder.append(ctx.IDENTIFIER().getText() + NEWLINE);
		}
	}

	@Override 
	public void enterDeclarationStatement(GKVParser.DeclarationStatementContext ctx) { 

		String datatype = this.typeMap.get(ctx.DATATYPE().getText());

		int variableCount = ctx.IDENTIFIER().size();

		for (int i = 0 ; i < variableCount; i++) {
			stringBuilder.append(datatype + WHITESPACE);
			stringBuilder.append(ctx.IDENTIFIER(i).getText().toUpperCase() + NEWLINE);
		}
	}
	
	@Override 
	public void exitDeclarationStatement(GKVParser.DeclarationStatementContext ctx) { }
	
	@Override
	public void enterFunctionCall(GKVParser.FunctionCallContext ctx) {}

	@Override 
	public void exitFunctionCall(GKVParser.FunctionCallContext ctx) { }

	@Override 
	public void enterIdList(GKVParser.IdListContext ctx) { 
		int parameterCount = ctx.IDENTIFIER().size();

		if (parameterCount == 1) {
			stringBuilder.append(this.typeMap.get(ctx.DATATYPE(0).getText()) + WHITESPACE);
			stringBuilder.append(ctx.IDENTIFIER(0).getText().toUpperCase() + NEWLINE);
		} else if (parameterCount > 1) {
			Iterator<TerminalNode> idIterator = ctx.IDENTIFIER().iterator();
			Iterator<TerminalNode> datatypeIterator = ctx.DATATYPE().iterator();
			do {
				stringBuilder.append(this.typeMap.get(datatypeIterator.next().getText()) + WHITESPACE);
				stringBuilder.append(idIterator.next().getText().toUpperCase() + NEWLINE);		
			} while (idIterator.hasNext());
		}
	}

	@Override 
	public void exitIdList(GKVParser.IdListContext ctx) { }

	@Override 
	public void enterParameters(GKVParser.ParametersContext ctx) { 

	}

	@Override 
	public void exitParameters(GKVParser.ParametersContext ctx) { }

	@Override 
	public void enterFunction(GKVParser.FunctionContext ctx) { 
		stringBuilder.append("FUNCSTART" + WHITESPACE);
		stringBuilder.append(ctx.IDENTIFIER().getText().toUpperCase() + WHITESPACE);
		stringBuilder.append(ctx.DATATYPE().getText().toUpperCase());
		stringBuilder.append(NEWLINE);
		stringBuilder.append(SCOPESTART);
		stringBuilder.append(NEWLINE);
	}

	@Override 
	public void exitFunction(GKVParser.FunctionContext ctx) { 
		stringBuilder.append(SCOPEEND + NEWLINE);
		stringBuilder.append(FUNCEND + NEWLINE);
	}

	@Override 
	public void enterReturnStatement(GKVParser.ReturnStatementContext ctx) { 
		stringBuilder.append(RET + WHITESPACE);
		// gkvnote: this is not the neat way of doing
		// this will be more clear when you evaluate expressions
		stringBuilder.append(ctx.expression().getText().toUpperCase() + NEWLINE);
	}
	
	@Override
	public void exitReturnStatement(GKVParser.ReturnStatementContext ctx) { }
	
	@Override
	public void enterDisplay(GKVParser.DisplayContext ctx) { }
	
	@Override
	public void exitDisplay(GKVParser.DisplayContext ctx) { }
	
	@Override 
	public void enterEveryRule(ParserRuleContext ctx) { }
	
	@Override 
	public void exitEveryRule(ParserRuleContext ctx) { }
	
	@Override 
	public void visitTerminal(TerminalNode node) { }
	
	@Override 
	public void visitErrorNode(ErrorNode node) { }
}
