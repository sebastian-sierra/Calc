package parser;

import lexer.VOp;
import state.State;

/**
 * Created by sebastian on 29/02/16.
 */
public class OpExpression extends Expression {

    private VOp vOp;
    private Expression expression1;
    private Expression expression2;

    public OpExpression(VOp vOp, Expression expression1, Expression expression2) {
        this.vOp = vOp;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public String toString() {
        return "OpExpression( "+vOp+";"+expression1+" ; "+expression2+" ) ";
    }

    @Override
    public int eval(State<Integer> variableState, State<Function> functionState) {
        switch (vOp) {
            case SUM: return expression1.eval(variableState, functionState) + expression2.eval(variableState, functionState);
            case MULT: return expression1.eval(variableState, functionState) * expression2.eval(variableState, functionState);
            case MINUS: return expression1.eval(variableState, functionState) - expression2.eval(variableState, functionState);
            case DIVISION: return expression1.eval(variableState, functionState) / expression2.eval(variableState,functionState );
            case COMP: return expression1.eval(variableState, functionState) == expression2.eval(variableState, functionState) ? 1 : 0;
            case LESS: return expression1.eval(variableState, functionState) < expression2.eval(variableState, functionState) ? 1 : 0;
            default: throw new RuntimeException();
        }

    }
}
