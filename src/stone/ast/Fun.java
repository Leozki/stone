package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/20.
 */
public class Fun extends ASTList {
    public Fun(List<ASTree> list) {
        super(list);
    }

    public ParameterList parameters() {
        return (ParameterList) child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(1);
    }

    @Override
    public String toString() {
        return "(fun " + parameters() + " " + body() + ")";
    }

    @Override
    public Object eval(Environment env) {
        return new Function(parameters(), body(), env);
    }
}
