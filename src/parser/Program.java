package parser;

import lexer.*;
import state.State;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 04/04/16.
 */
public class Program extends AST {

    private LinkedList<Function> functions;
    private Body body;

    public Program(LinkedList<Function> functions, Body body) {
        this.functions = functions;
        this.body = body;
    }

    public static Program parse(Token token, LinkedList<Function> functions) throws IOException, UnexpectedCharacter {

        if (token instanceof LPar) {
            Token token2 = SLexer.getToken();
            if (token2 instanceof lexer.Define) {

                Function function = Function.parseTail(SLexer.getToken());

                functions.add(function);

                return parse(SLexer.getToken(), functions);
            }
            else {
                Body body = Body.parseTail(token2, new LinkedList<>());
                return new Program(functions, body);
            }
        }
        else {
            return parseSimpleProgram(token, functions);
        }

    }

    private static Program parseSimpleProgram(Token token, LinkedList<Function> functions) throws IOException, UnexpectedCharacter {
        Body body = Body.parse(token, new LinkedList<>());
        return new Program(functions, body);
    }

    public int eval() {
        State<Function> functionState = new State<>();
        State<Integer> variableState = new State<>();

        functions.forEach(function -> function.eval(functionState));

        return body.eval(variableState, functionState);

    }


}
