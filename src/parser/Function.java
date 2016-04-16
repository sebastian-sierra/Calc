package parser;

import lexer.RPar;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import state.State;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by sebastian on 04/04/16.
 */
public class Function extends AST {

    private Head head;
    private Body body;

    public Function(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public Body getBody() {
        return body;
    }

    public static Function parseTail(Token token) throws IOException, UnexpectedCharacter {
        Head head = Head.parse(token);
        Body body = Body.parse(SLexer.getToken(), new LinkedList<>());
        if (SLexer.getToken() instanceof RPar) {
            return new Function(head, body);
        }
        else throw new RuntimeException();
    }

    public void eval(State<Function> state) {
        if (state.lookup(head.getFunctionIdentifier()) == null) {
            state.bind(this.head.getFunctionIdentifier(), this);
        } else throw new RuntimeException("Function "+head.getFunctionIdentifier()+" is already defined");
    }

}
