package stone;

import stone.ast.ASTree;

/**
 * Created by master on 2017/7/13.
 */
public class StoneException extends RuntimeException {
    public StoneException(String s) {
        super(s);
    }
    public StoneException(String s, ASTree t) {
        super(s + t.location());
    }
}
