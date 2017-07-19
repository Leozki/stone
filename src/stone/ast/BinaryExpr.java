package stone.ast;

import stone.env.Environment;
import stone.exception.StoneException;

import java.util.List;

/**
 * Created by master on 2017/7/14.
 */
public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> list) {
        super(list);
    }

    /**
     * 返回左树
     *
     * @return
     */
    public ASTree left() {
        return child(0);
    }

    /**
     * 返回右树
     *
     * @return
     */
    public ASTree right() {
        return child(2);
    }

    public String operator() {
        return ((ASTLeaf) child(1)).token().getText();
    }

    @Override
    public Object eval(Environment env) {
        String op = operator();
        if ("=".equals(op)) {
            // 赋值
            Object right = right().eval(env);
            return computeAssign(env, right);
        } else {
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left, op, right);
        }
    }

    protected Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer) left, op, (Integer) right);
        } else {
            if (op.equals("+")) {
                return String.valueOf(left) + String.valueOf(right);
            } else if (op.equals("==")) {
                if (left == null)
                    return right == null ? True : False;
                else
                    return left.equals(right) ? True : False;
            } else {
                return new StoneException("bad type", this);
            }
        }
    }

    protected Object computeNumber(Integer left, String op, Integer right) {
        int a = left;
        int b = right;
        if (op.equals("+"))
            return a + b;
        else if (op.equals("-"))
            return a - b;
        else if (op.equals("*"))
            return a * b;
        else if (op.equals("/"))
            return a / b;
        else if (op.equals("%"))
            return a % b;
        else if (op.equals("=="))
            return a == b ? True : False;
        else if (op.equals(">"))
            return a > b ? True : False;
        else if (op.equals("<"))
            return a < b ? True : False;
        else
            throw new StoneException("bad operator", this);
    }

    protected Object computeAssign(Environment env, Object rvalue) {
        ASTree l = left();
        // 左值必须是变量
        if (l instanceof Name) {
            env.put(((Name) l).name(), rvalue);
            return rvalue;
        } else {
            throw new StoneException("bad assignment", this);
        }
    }
}
