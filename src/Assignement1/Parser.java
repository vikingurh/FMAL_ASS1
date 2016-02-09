package Assignement1;

import Assignement1.Token.TokenCode;

public class Parser {
	private Lexer lex;
	private Token currToken;
	
	Parser(Lexer lexer){
		this.lex = lexer;
	}
	public void parse(){
		currToken = lex.nextToken();
		Statements();
	}
	
	
	private void Statements(){
		if (currToken.tCode == TokenCode.END){
			return;
		}
		Statement();
		if (currToken.tCode == TokenCode.SEMICOL){
			currToken = lex.nextToken();
			Statements();
		}
		else{
			error();
		}
	}
	
	private void Statement(){
		if (currToken.tCode == TokenCode.ID){
			System.out.print("PUSH ");
			System.out.println(currToken.lexeme);
			currToken = lex.nextToken();
			if (currToken.tCode == TokenCode.ASSIGN){
				currToken = lex.nextToken();
				Expr();
				System.out.println("ASSIGN");
			}
			else{
				error();
			}
		}
		else if (currToken.tCode == TokenCode.PRINT){
			currToken = lex.nextToken();
			if (currToken.tCode == TokenCode.ID){
				System.out.print("PUSH ");
				System.out.println(currToken.lexeme);
				System.out.println("PRINT");
				currToken = lex.nextToken();
			}
			else{
				error();
			}
		}
		else{
			error();
		}
	}
	
	private void Expr(){
		Term();
		if (currToken.tCode == TokenCode.ADD || currToken.tCode == TokenCode.SUB){
			TokenCode tc = currToken.tCode;
			currToken = lex.nextToken();
			Expr();
			System.out.println(tc);
		}
		else if (currToken.tCode == TokenCode.SEMICOL || currToken.tCode == TokenCode.RPAREN){
			return;
		}
		else{
			error();
		}
	}
	
	private void Term(){
		Factor();
		if (currToken.tCode == TokenCode.MULT){
			TokenCode tc = currToken.tCode;
			currToken = lex.nextToken();
			Term();
			System.out.println(tc);
		}
		else if (currToken.tCode == TokenCode.SEMICOL || currToken.tCode == TokenCode.ADD || currToken.tCode == TokenCode.SUB || currToken.tCode == TokenCode.RPAREN){
			return;
		}
		else{
			error();
		}
	}
	
	
	private void Factor(){
		if (currToken.tCode == TokenCode.INT){
			System.out.print("PUSH ");
			System.out.println(currToken.lexeme);
			currToken = lex.nextToken();
		}
		else if (currToken.tCode == TokenCode.ID){
			System.out.print("PUSH ");
			System.out.println(currToken.lexeme);
			currToken = lex.nextToken();
		}
		else if (currToken.tCode == TokenCode.LPAREN){
			currToken = lex.nextToken();
			Expr();
			if (currToken.tCode == TokenCode.RPAREN){
				currToken = lex.nextToken();
			}
			else{
				error();
			}
		}
		else{
			error();
		}
	}
	
	private void error(){
		System.out.println("Syntax error!");
		System.exit(1);
	}
}
