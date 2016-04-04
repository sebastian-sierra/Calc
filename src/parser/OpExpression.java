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
    public int eval(State<Integer> state) {
        switch (vOp) {
            case SUM: return expression1.eval(state) + expression2.eval(state);
            case MULT: return expression1.eval(state) * expression2.eval(state);
            case MINUS: return expression1.eval(state) - expression2.eval(state);
            case DIVISION: return expression1.eval(state) / expression2.eval(state);
            case COMP: return expression1.eval(state) == expression2.eval(state) ? 1 : 0;
            case LESS: return expression1.eval(state) < expression2.eval(state) ? 1 : 0;
            default: throw new RuntimeException();
        }

    }
}
