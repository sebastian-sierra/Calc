package calc;

import lexer.Lexer;
import lexer.Token;

import java.util.List;

public class  TextLexer {

	/**
	 * @param args - arg[0] is the filename of the file to interpret.
	 */
	public static void main(String[] args) {
		List<Token> tokens;
		
		try {
			Lexer lexer = new Lexer(args[0]);
			tokens = lexer.lex(); 
			// output of the result	
			for (Token token : tokens) {
				System.out.println(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
