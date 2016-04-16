package parser;

import lexer.*;
import state.State;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sebastian on 04/04/16.
 */
public class FunctionCall extends Expression {

    private String identifier;
    private LinkedList<Expression> parameters;

    public FunctionCall(String identifier, LinkedList<Expression> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public static FunctionCall parse(Token token, LinkedList<Expression> parameters, Identifier functionIdentifier) throws IOException, UnexpectedCharacter {

        if (token instanceof RPar) {
            return new FunctionCall(functionIdentifier.getValue(), parameters);
        }
        else {
            Expression parameter = Expression.parse(token);
            parameters.add(parameter);
            return parse(SLexer.getToken(), parameters, functionIdentifier);
        }
    }

    @Override
    public int eval(State<Integer> variableState, State<Function> functionState) {
        Function function = functionState.lookup(identifier);
        if (function != null) {
            State<Integer> localState = bindArguments(function.getHead().getVariables(), parameters, variableState, functionState);
            return function.getBody().eval(localState, functionState);
        }
        else throw new RuntimeException("Function " + identifier + " not found");

    }

    private State<Integer> bindArguments(List<Variable> formals, List<Expression> args, State<Integer> variableState, State<Function> functionState) {
        State<Integer> localState = new State<>();

        for (int i = 0; i < formals.size(); i++) {
            localState.bind(formals.get(i).getValue(), args.get(i).eval(variableState, functionState));
        }

        return localState;
    }
}
