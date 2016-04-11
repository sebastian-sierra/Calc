package parser;

import state.State;

/**
 * Created by sebastian on 29/02/16.
 */
public class UnaryMinusExpression extends Expression {

    private Expression expression;

    public UnaryMinusExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "UnaryMinusExpression( "+expression+" )";
    }

    @Override
    public int eval(State<Integer> variableState, State<Function> functionState) {
        return - expression.eval(variableState, functionState);
    }
}
