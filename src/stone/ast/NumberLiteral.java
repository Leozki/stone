package stone.ast;

import stone.Environment;
import stone.Token;

/**
 * Created by master on 2017/7/14.
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token token) {
        super(token);
    }

    public int value() {
        return token().getNumber();
    }

    @Override
    public Object eval(Environment env) {
        return value();
    }
}
