package lexer;

/**
 * Created by sebastian on 01/02/16.
 */
public class Op extends Token {
    private VOp value;

    public Op(VOp value) {
        this.value = value;
    }

    public VOp getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OP(" + this.value + ")";
    }
}
