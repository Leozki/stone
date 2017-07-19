package stone.run;

import stone.*;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import stone.ast.BasicParser;
import stone.env.BasicEnv;
import stone.env.Environment;
import stone.exception.ParseException;

/**
 * Created by master on 2017/7/18.
 */
public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv());
    }

    public static void run(BasicParser bp, Environment env) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
