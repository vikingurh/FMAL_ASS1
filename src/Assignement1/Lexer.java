package Assignement1;

import java.util.Scanner;

import Assignement1.Token.TokenCode;

public class Lexer {
	static Scanner sc = new Scanner(System.in);
	public Token nextToken() {
		sc.useDelimiter("");
		String tokenString = "";
	    String c = sc.next();
	    if (c.equals(" ")){
	    	while (c.equals(" ")){
	    		c = sc.next();
	    	}
	    }
	    if (Character.isAlphabetic(c.charAt(0))){
	    	while(Character.isDigit(c.charAt(0)) || Character.isAlphabetic(c.charAt(0))){
		    	tokenString += c;
		    	if (sc.hasNext("\\)") || sc.hasNext(";") || sc.hasNext("\\+") || sc.hasNext("\\-") || sc.hasNext("\\*") || sc.hasNext("=")){
		    		break;
		    	}
		    	c = sc.next();
		    }
	    	if (tokenString.equals("print")){
	    		return new Token(tokenString, TokenCode.PRINT);
	    	}
	    	else if (tokenString.equals("end")){
	    		
	    		return new Token(tokenString, TokenCode.END);
	    	}
	    	else{
	    		if (isValid(tokenString)){
	    			return new Token(tokenString, TokenCode.ID);
	    		}
	    		else{
	    			return new Token(tokenString, TokenCode.ERROR);
	    		}
	    	}
	    }
	    else if (c.equals("=")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.ASSIGN);
	    }
	    else if (Character.isDigit(c.charAt(0))){
	    	while(Character.isDigit(c.charAt(0))){
		    	tokenString += c;
		    	if (!sc.hasNextInt()){
		    		break;
		    	}
		    	c = sc.next();	
		    }
	    	return new Token(tokenString, TokenCode.INT);
	    }
	    else if (c.equals(";")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.SEMICOL);
	    }
	    else if (c.equals("*")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.MULT);
	    }
	    else if (c.equals("(")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.LPAREN);
	    }
	    else if (c.equals("-")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.SUB);
	    }
	    else if (c.equals(")")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.RPAREN);
	    }
	    else if (c.equals("+")){
	    	tokenString += c;
	    	return new Token(tokenString, TokenCode.ADD);
	    }
	    else{
	    	return new Token(tokenString, TokenCode.ERROR);
	    }
	}
	private boolean isValid(String s){
		for (int i = 0; i < s.length(); i++){
			if (!Character.isDigit(s.charAt(i)) && !Character.isAlphabetic(s.charAt(0))){
				return false;
			}
		}
		return true;
	}
}
