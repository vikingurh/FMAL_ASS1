package Assignement1;

public class Token {
	String lexeme;
	TokenCode tCode;
	enum TokenCode { ID, ASSIGN, SEMICOL, INT, ADD, SUB, MULT, LPAREN, RPAREN, PRINT, END, ERROR };
	public Token(String lex, TokenCode code){
		this.lexeme = lex;
		this.tCode = code;	
	}
}
