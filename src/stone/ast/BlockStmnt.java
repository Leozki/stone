package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/17.
 */
public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for (ASTree t : this) {
            if (!(t instanceof NullStmnt))
                result = t.eval(env);
        }
        return result;
    }
}
