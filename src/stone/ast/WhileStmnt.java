package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/17.
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for (; ; ) {
            Object c = condition().eval(env);
            if (c instanceof Integer && (Integer) c == False)
                return result;
            else
                result = body().eval(env);
        }
    }
}
