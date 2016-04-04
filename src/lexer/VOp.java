package lexer;

/**
 * Created by sebastian on 01/02/16.
 */
public enum VOp {
    SUM("+"),
    MINUS("-"),
    MULT("*"),
    DIVISION("/"),
    COMP("=="),
    LESS("<");

    private String value;

    VOp(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
