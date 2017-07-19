package stone.ast;

import stone.env.Environment;
import stone.exception.StoneException;

import java.util.List;

/**
 * Created by master on 2017/7/18.
 */
public class Arguments extends Postfix {
    public Arguments(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment callerEnv, Object value) {
        if (!(value instanceof Function))
            throw new StoneException("bad function", this);
        Function func = (Function) value;
        ParameterList params = func.parameters();
        if (size() != params.size())
            throw new StoneException("bad number of arguments", this);
        Environment newEnv = func.makeEnv();
        int num = 0;
        for (ASTree t : this) {
            params.eval(newEnv, num++, t.eval(callerEnv));
        }
        return ((BlockStmnt) func.body()).eval(newEnv);
    }

    public int size() {
        return numChildren();
    }

    @Override
    public Object eval(Environment env) {
        return super.eval(env);
    }
}
