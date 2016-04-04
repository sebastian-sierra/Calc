package lexer;

/**
 * Created by sebastian on 15/02/16.
 */
public class Identifier extends Token {

    private String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IDENTIFIER("+this.value+")";
    }
}
