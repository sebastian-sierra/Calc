package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private FileReader in;
    private int i; // current ASCII character (coded as an integer)

    public Lexer(String filename) throws IOException {
        File file = new File(filename);
        try {
            in = new FileReader(file);
            i = in.read(); // initialize lexer
        } catch (FileNotFoundException e) {
            System.err.println("File : " + filename + " not found");
            throw e; // pass the exception up the stack
        } catch (IOException e) {
            in.close(); // close the reader
            throw e; // pass the exception up the stack
        }
    }

    public List<Token> lex() throws UnexpectedCharacter, IOException {
        // return the list of tokens recorded in the file
        List<Token> tokens = new ArrayList<Token>();

        try {
            Token token = getToken();

            while (!(token instanceof EOF)) {
                tokens.add(token);
                token = getToken();
            }
            tokens.add(token); // this is the EOF token
        } catch (IOException e) {
            in.close(); // close the reader
            throw e; // pass the exception up the stack
        }
        return tokens;
    }

    public Token getToken() throws UnexpectedCharacter, IOException {
        switch (i) {
            case -1:
                in.close();
                return new EOF();
            case '(':
                i = in.read();
                return new LPar();
            case ' ':
                i = in.read();
                return getToken();
            case 10:
                i = in.read();
                return getToken();
            case ')':
                i = in.read();
                return new RPar();
            case '+':
                i = in.read();
                return new Op(VOp.SUM);
            case '-':
                i = in.read();
                return new Op(VOp.MINUS);
            case '*':
                i = in.read();
                return new Op(VOp.MULT);
            case '/':
                i = in.read();
                return new Op(VOp.DIVISION);
            case '<':
                i = in.read();
                return new Op(VOp.LESS);
            case '=':
                i = in.read();
                if (i == '=') {
                    i = in.read();
                    return new Op(VOp.COMP);
                }
                else {
                    i = in.read();
                    return new Equal();
                }
            case '0':
                i = in.read();
                return new Literal(0);

            default:

                if ('1' <= i && i <= '9') {

                    String value = Character.getNumericValue(i) + "";
                    i = in.read();
                    while ('0' <= i && i <= '9') {
                        value += Character.getNumericValue(i);
                        i = in.read();
                    }
                    return new Literal(Integer.parseInt(value));

                } else if ('a' <= i && i <= 'z') {

                    String value = (char)i+"";
                    if (i == 'i') {
                        i = in.read();
                        value += (char)i;
                        if (i == 'f') {
                            i = in.read();
                            if (i == ' ') {
                                i = in.read();
                                return new If();
                            } else {
                                value += (char)i;
                            }
                        }
                    }

                    else if (i == 'd') {
                        i = in.read();
                        value += (char)i;
                        if (i == 'e') {
                            i = in.read();
                            value += (char)i;
                            if (i == 'f') {
                                i = in.read();
                                value += (char)i;
                                if (i == 'i') {
                                    i = in.read();
                                    value += (char)i;
                                    if (i == 'n') {
                                        i = in.read();
                                        value += (char)i;
                                        if (i == 'e') {
                                            i = in.read();
                                            if (i == ' ') {
                                                i = in.read();
                                                return new Define();
                                            } else {
                                                value += (char)i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    i = in.read();
                    while ('a' <= i && i <= 'z' || '0' <= i && i <= '9') {
                        value += (char)i;
                        i = in.read();
                    }

                    return new Identifier(value);


                } else {

                    throw new UnexpectedCharacter(i);

                }
        }
    }
}


