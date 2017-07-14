package stone.ast;

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
}
