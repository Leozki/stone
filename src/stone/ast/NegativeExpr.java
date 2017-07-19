package stone.ast;

import stone.env.Environment;
import stone.exception.StoneException;

import java.util.List;

/**
 * Created by master on 2017/7/17.
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object eval(Environment env) {
        Object v = operand().eval(env);
        if (v instanceof Integer)
            return -(Integer) v;
        else
            return new StoneException("bad type for -", this);
    }
}
