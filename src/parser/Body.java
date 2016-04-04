package parser;

import lexer.*;
import state.State;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 21/03/16.
 */
public class Body extends AST {

    private LinkedList<Definition> definitions;
    private Expression expression;

    public Body(LinkedList<Definition> definitions, Expression expression) {
        this.definitions = definitions;
        this.expression = expression;
    }

    public static Body parse(Token token, LinkedList<Definition> definitions ) throws IOException, UnexpectedCharacter {

        if (token instanceof LPar) {
            Token token2 = SLexer.getToken();
            if (token2 instanceof Equal) {
                Definition def = Definition.parse(SLexer.getToken());

                definitions.add(def);

                return parse(SLexer.getToken(), definitions);
            }
            else {
                Expression exp = Expression.parseCompositeExpressionTail(token2);
                return new Body(definitions, exp);
            }
        }
        else {
            return parseSimpleBody(token, definitions);
        }

    }

    private static Body parseSimpleBody(Token token, LinkedList<Definition> definitions) {
        Expression exp = Expression.parseSimpleExpression(token);
        return new Body(definitions, exp);
    }

    public int eval() {
        State<Integer> state = new State<>();
        definitions.forEach(definition -> definition.eval(state));
        return expression.eval(state);
    }

    @Override
    public String toString() {
        return "BODY(\n" +
                definitions+"\n" +
                expression+")";
    }
}
