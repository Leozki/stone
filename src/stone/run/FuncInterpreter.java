package stone.run;

import stone.env.NestedEnv;
import stone.ast.FuncParser;
import stone.exception.ParseException;

/**
 * Created by master on 2017/7/19.
 */
public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new FuncParser(), new NestedEnv());
    }
}
