grammar GKV;

options {
  language = Java;
}

@parser:: header {
//  package edu.asu.msse.gkv;
}

@lexer::header {
//  package edu.asu.msse.gkv;
} 

program 
	: 
    ( 	sequenceOfStatements
    | 	function
    )+ 
    ;

sequenceOfStatements
	: 
	(	simpleStatement 
	| 	compoundStatement
	)+
	; 

simpleStatement
	: 
	(	assignmentStatement 
	| 	declarationStatement 
	| 	functionCall 
	| 	display
	)? 	';'
	;

compoundStatement
	: 
	(	ifelseStatement 
	| 	loop
	)
	;

assignmentStatement
	: IDENTIFIER ASSIGNMENT_SYMBOL (expression | functionCall)
	;

ifelseStatement: ifStatement (elseStatement)?;

ifStatement
	:	CK_IF condition CK_THEN O_BRACE
			sequenceOfStatements 
		C_BRACE 
	; 

elseStatement
	:	CK_ELSE O_BRACE 
			sequenceOfStatements 
		C_BRACE
	;

loop
	:	'while' condition O_BRACE  
			sequenceOfStatements 
		C_BRACE;

condition
	:	expression
	;

expression
	:	relation
	(	RELATIONOP relation 
	)*
	;

relation
	:	simpleExpression 
	(	COMPK_KEYWORDS simpleExpression
	)?
	;

simpleExpression
	:	term 
	(	ADDING_OPERATOR term
	)*
	;

term
	:	factor 
	(	MULTIPLYING_OPERATOR factor
	)*
	; 
 
factor
	: 
	(	INTEGER_LITERAL 
	|	DECIMAL_LITERAL 
	| 	IDENTIFIER 
	|   BOOLEAN
	| 	'(' expression ')'
	)
	;

declarationStatement
	:	DATATYPE IDENTIFIER (COMMA IDENTIFIER)* ;

functionCall
	:	FK_CALL IDENTIFIER (FK_WITH parameters)?
	;

parameters
	:	expression (COMMA expression)*
	;

function 
	: 	FK_FUNCTION IDENTIFIER (FK_USES idList)? FK_RETURNS DATATYPE
        O_BRACE 
        	(sequenceOfStatements)?
          	returnStatement
       	C_BRACE
    ;

returnStatement: FK_RETURN expression ';';

idList: DATATYPE IDENTIFIER (COMMA DATATYPE IDENTIFIER)*;

display
	:	'show' (STRING_LITERAL | INTEGER_LITERAL | DECIMAL_LITERAL | IDENTIFIER)
	;

INTEGER_LITERAL
	:	(('+'|'-')? NUMBER+)
	;
	
DECIMAL_LITERAL
	:	(('+'|'-')? NUMBER'.'NUMBER+)
	;

STRING_LITERAL
    :	'"' ( c = ~('"' | '\n' | '\r') )* '"'
    ;
 
fragment NUMBER
	:	('0'..'9')
	;

RELATIONOP
	: ('and' | 'or');
	 
BOOLEAN
	:	('true' | 'false')
	;
	
DATATYPE
	:	('integer' | 'decimal' | 'boolean')
	;

FK_FUNCTION 
	:	'function'
	;
	
FK_USES 
	:	'uses'
	;
	
FK_RETURNS 
	:	'returns'
	;
	
FK_RETURN
	:	'return'
	;

O_BRACE
	:	'{'
	;
	
C_BRACE
	:	'}'
	;

CK_IF
	:	'if'
	;
	
CK_ELSE
	:	'else'
	;
	
CK_THEN
	:	'then'
	;
	
CK_ELSIF
	:	'elsif'
	;

FK_CALL
	:	'call'
	;
	
FK_WITH
	:	'with'
	;
	
ASSIGNMENT_SYMBOL
	:	'='
	;

COMMA
	:	','
	;

COMPK_KEYWORDS
	: 
	(	'equalTo' 
	| 	'lessThan'
	| 	'greaterThan' 
	|	'lessThanOrEqualTo' 
	| 	'greaterThanOrEqualTo' 
	|	'notEqualTo'
	)
	;
	
IDENTIFIER
	:
	(	'a'..'z'
	| 	'A'..'Z'
	)+
	; 

ADDING_OPERATOR
	:	('+' | '-')
	;
	
MULTIPLYING_OPERATOR
	:	('*' | '/')
	;
	
WHITESPACE 
	: 
	(	' ' 
	| 	'\t' 
	| 	'\r' 
	| 	'\n' 
	|	'\f'
	)+ -> skip 
	;