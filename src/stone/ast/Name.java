package stone.ast;

import stone.Environment;
import stone.StoneException;
import stone.Token;

/**
 * Created by master on 2017/7/14.
 */
public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return token().getText();
    }

    @Override
    public Object eval(Environment env) {
        Object value = env.get(name());
        if (value == null)
            throw new StoneException("undefined name: " + name(), this);
        else
            return value;
    }
}
