package parser;

import lexer.*;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 04/04/16.
 */
public class Head extends AST {

    private String functionIdentifier;
    private LinkedList<Variable> variables;

    public Head(String functionIdentifier, LinkedList<Variable> variables) {
        this.functionIdentifier = functionIdentifier;
        this.variables = variables;
    }

    public String getFunctionIdentifier() {
        return functionIdentifier;
    }

    public LinkedList<Variable> getVariables() {
        return variables;
    }

    public static Head parse(Token token) throws IOException, UnexpectedCharacter {
        if (token instanceof LPar) {
            token = SLexer.getToken();
            if (token instanceof Identifier) {

                Identifier functionIdentifier = (Identifier) token;


                LinkedList<Variable> variables = new LinkedList<>();

                token = SLexer.getToken();
                while (token instanceof Identifier) {
                    Identifier variableIdentifier = (Identifier) token;
                    Variable variable = new Variable(variableIdentifier.getValue());
                    variables.add(variable);
                    token = SLexer.getToken();
                }
                if (token instanceof RPar) {
                    return new Head(functionIdentifier.getValue(), variables);
                }
                else throw new RuntimeException();
            }
            else throw new RuntimeException();
        }
        else throw new RuntimeException();
    }

}
