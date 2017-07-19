package stone;

import stone.exception.ParseException;

/**
 * Created by master on 2017/7/13.
 */
public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        for (Token t; (t = lexer.read()) != Token.EOF; ) {
            System.out.println("=> " + t.toString());
        }
    }
}
