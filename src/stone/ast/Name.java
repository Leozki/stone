package stone.ast;

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
}
