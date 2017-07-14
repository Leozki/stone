package stone.ast;

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
}
