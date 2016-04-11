package parser;

import lexer.*;
import state.State;

import java.io.IOException;

/**
 * Created by sebastian on 21/03/16.
 */
public class Definition extends AST {

    private Variable variable;
    private Expression expression;

    public Definition(Variable variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public static Definition parse(Token token) throws IOException, UnexpectedCharacter {

        Identifier identifier = (Identifier) token;
        Variable variable = new Variable(identifier.getValue());
        Expression expression = Expression.parse(SLexer.getToken());
        Definition definition = new Definition(variable, expression);
        Token nextToken = SLexer.getToken();
        if (nextToken instanceof RPar) {

            return definition;
        }
        else throw new RuntimeException();

    }

    public void eval (State<Integer> variableState, State<Function> functionState) {
        variableState.bind(variable.getValue(), expression.eval(variableState, functionState));
    }

    @Override
    public String toString() {
        return "DEFINITION("+variable+", "+expression+")";
    }
}
