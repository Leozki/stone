package stone.ast;

import stone.env.Environment;
import stone.env.NestedEnv;

/**
 * Created by master on 2017/7/19.
 */
public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;

    public Function(ParameterList parameters, BlockStmnt body, Environment env) {
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmnt body() {
        return body;
    }

    public Environment makeEnv() {
        return new NestedEnv(env);
    }

    @Override
    public String toString() {
        return "<func:" + hashCode() + ">";
    }
}
