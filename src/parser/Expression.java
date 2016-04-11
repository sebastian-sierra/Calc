package parser;

import lexer.*;
import state.State;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 29/02/16.
 */
public abstract class Expression extends AST {


    public static Expression parse(Token token) throws IOException, UnexpectedCharacter {
        if (token instanceof lexer.Literal) {
            return new Literal(((lexer.Literal) token).getValue());
        }
        else if (token instanceof LPar) {
            Token nextToken = SLexer.getToken();
            return parseCompositeExpressionTail(nextToken);
        }
        else if (token instanceof lexer.Identifier) {
            Identifier identifier = (Identifier) token;
            return new Variable(identifier.getValue());
        }
        else throw new RuntimeException();
    }

    public abstract int eval(State<Integer> variableState, State<Function> functionState);

    public static Expression parseCompositeExpressionTail(Token token) throws IOException, UnexpectedCharacter {

        if (token instanceof If) {
            Expression exp1 = Expression.parse(SLexer.getToken());
            Expression exp2 = Expression.parse(SLexer.getToken());
            Expression exp3 = Expression.parse(SLexer.getToken());

            if (SLexer.getToken() instanceof RPar) {
                return new ConditionalExpression(exp1, exp2, exp3);
            }
            else throw new RuntimeException();


        }
        else if (token instanceof Op) {
            Op op = (Op) token;
            if (op.getValue() == VOp.MINUS) {
                Expression exp1 = Expression.parse(SLexer.getToken());
                Token nextToken = SLexer.getToken();

                if (nextToken instanceof RPar) {
                    return new UnaryMinusExpression(exp1);
                }
                else {
                    Expression exp2 = Expression.parse(nextToken);

                    if (SLexer.getToken() instanceof RPar) {
                        return new OpExpression(VOp.MINUS, exp1, exp2);
                    }
                    else throw new RuntimeException();

                }
            }
            else {
                Expression exp1 = Expression.parse(SLexer.getToken());
                Expression exp2 = Expression.parse(SLexer.getToken());

                if (SLexer.getToken() instanceof RPar) {
                    return new OpExpression(op.getValue(), exp1, exp2);
                }
                else throw new RuntimeException();
            }
        }
        else if (token instanceof Identifier) {
            Identifier functionIdentifier = (Identifier) token;
            return FunctionCall.parse(SLexer.getToken(), new LinkedList<>(), functionIdentifier);
        }
        else throw new RuntimeException();

    }

    public static Expression parseSimpleExpression(Token token) {
        if (token instanceof lexer.Literal) {
            return new Literal(((lexer.Literal) token).getValue());
        }
        else if (token instanceof lexer.Identifier) {
            Identifier identifier = (Identifier) token;
            return new Variable(identifier.getValue());
        }
        else throw new RuntimeException();
    }

}
