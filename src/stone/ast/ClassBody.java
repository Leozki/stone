package stone.ast;

import stone.env.Environment;

import java.util.List;

public class ClassBody extends ASTList{
    public ClassBody(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        for (ASTree t : this)
            t.eval(env);
        return null;
    }
}
