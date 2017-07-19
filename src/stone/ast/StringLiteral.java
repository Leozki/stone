package stone.ast;

import stone.env.Environment;
import stone.Token;

/**
 * Created by master on 2017/7/17.
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }

    public String value() {
        return token().getText();
    }

    @Override
    public Object eval(Environment env) {
        return value();
    }
}
