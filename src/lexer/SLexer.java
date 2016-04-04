package lexer;

import java.io.IOException;

/**
 * Created by sebastian on 29/02/16.
 */
public class SLexer {

    private static Lexer lexer;

    public static void init(String filename) throws IOException {
        lexer = new Lexer(filename);
    }

    public static Token getToken() throws IOException, UnexpectedCharacter {
        return lexer.getToken();
    }

}
