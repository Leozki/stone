package stone.run;

import stone.ast.ClosureParser;
import stone.ast.Natives;
import stone.env.NestedEnv;
import stone.exception.ParseException;

/**
 * Created by master on 2017/7/20.
 */
public class NativeInterpreter extends BasicInterpreter {
    /*
    def fib(n) {
        if n < 2 {
            n
        } else {
            fib(n-1)+fib(n-2)
        }
    }
    t = currentTime()
    fib 15
    print currentTime() - t + " msec"

     */
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new Natives().environment(new NestedEnv()));
    }
}
