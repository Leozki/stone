package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/19.
 */
public abstract class Postfix extends ASTList {
    public Postfix(List<ASTree> list) {
        super(list);
    }

    public abstract Object eval(Environment env, Object value);
}
