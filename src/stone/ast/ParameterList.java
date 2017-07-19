package stone.ast;

import stone.env.Environment;

import java.util.List;

/**
 * Created by master on 2017/7/18.
 */
public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> list) {
        super(list);
    }

    public String name(int i) {
        return ((ASTLeaf) child(i)).token().getText();
    }

    public int size() {
        return numChildren();
    }

    public void eval(Environment env, int index, Object value) {
        env.putNew(name(index), value);
    }
}
