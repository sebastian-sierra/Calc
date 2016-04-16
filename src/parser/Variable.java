package parser;

import state.State;

/**
 * Created by sebastian on 21/03/16.
 */
public class Variable extends Expression {

    private String value;


    public Variable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VARIABLE(" + value +")";
    }

    @Override
    public int eval(State<Integer> variableState, State<Function> functionState) {

        Integer variableValue = variableState.lookup(this.getValue());

        if(variableValue!=null) {
            return variableValue;
        } else {
            throw new RuntimeException("Variable "+this.getValue()+" not found");
        }

    }
}
