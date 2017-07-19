package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/18.
 */
public class Defstmnt extends ASTList {
    public Defstmnt(List<ASTree> list) {
        super(list);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList) child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }

    @Override
    public Object eval(Environment env) {
        env.putNew(name(), new Function(parameters(), body(), env));
        return name();
    }
}
