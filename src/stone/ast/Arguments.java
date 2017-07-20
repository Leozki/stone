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

        if (!((value instanceof Function) || (value instanceof NativeFunction)))
            throw new StoneException("bad function", this);

        if (value instanceof Function) {
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
        } else {
            NativeFunction func = (NativeFunction) value;
            int nparams = func.numOfParameters();
            if (size() != nparams) {
                throw new StoneException("bad number of arguments", this);
            }
            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a : this) {
                args[num++] = a.eval(callerEnv);
            }
            return func.invoke(args, this);
        }


    }

    public int size() {
        return numChildren();
    }

    @Override
    public Object eval(Environment env) {
        return super.eval(env);
    }
}
