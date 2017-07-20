package stone.run;

import stone.ast.ClosureParser;
import stone.env.NestedEnv;
import stone.exception.ParseException;

/**
 * Created by master on 2017/7/20.
 */
public class ClosureInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new NestedEnv());
    }
}
