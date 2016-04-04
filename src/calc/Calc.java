package calc;

import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import parser.Body;
import parser.Definition;
import parser.Expression;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 29/02/16.
 */
public class Calc {

    public static void main(String[] args) throws IOException, UnexpectedCharacter {
        SLexer.init(args[0]);
        Token t = SLexer.getToken();
        Body body = Body.parse(t, new LinkedList<>());
        //System.out.println(body);
        System.out.println(body.eval());
    }
}
