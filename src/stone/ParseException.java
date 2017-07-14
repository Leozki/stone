package stone;

import java.io.IOException;

/**
 * Created by master on 2017/7/13.
 */
public class ParseException extends Exception {
    public ParseException(Token token) {
        this("", token);
    }

    public ParseException(String msg, Token token) {
        super("syntax error around " + location(token) + ". " + msg);
    }

    private static String location(Token token) {
        if (token == Token.EOF) {
            return "the last line";
        } else {
            return "\"" + token.getText() + "\"at line " + token.getLineNumber();
        }

    }

    public ParseException(IOException e) {
        super(e);
    }

    public ParseException(String s) {
        super(s);
    }
}
