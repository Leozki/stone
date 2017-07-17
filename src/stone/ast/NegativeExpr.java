package stone.ast;

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
}
