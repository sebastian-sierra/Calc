package parser;

import state.State;

/**
 * Created by sebastian on 29/02/16.
 */
public class Literal extends Expression {

    private int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Literal("+value+")";
    }

    @Override
    public int eval(State<Integer> variableState, State<Function> functionState) {
        return value;
    }
}
