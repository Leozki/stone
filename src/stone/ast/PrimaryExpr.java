package stone.ast;

import java.util.List;

/**
 * Created by master on 2017/7/17.
 */
public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> list) {
        super(list);
    }

    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }
}
