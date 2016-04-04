package lexer;

/**
 * Created by sebastian on 15/02/16.
 */
public class Literal extends Token {

    private int value;

    public Literal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LITERAL("+this.value+")";
    }
}
