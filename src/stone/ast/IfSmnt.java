package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/17.
 */
public class IfSmnt extends ASTList {
    public IfSmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree thenBlock() {
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
    }

    @Override
    public Object eval(Environment env) {
        Object c = condition().eval(env);
        if (c instanceof Integer && (Integer) c != False)
            return thenBlock().eval(env);
        else {
            ASTree b = elseBlock();
            if (b == null)
                return 0;
            else
                return b.eval(env);
        }
    }
}
