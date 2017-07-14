package stone.ast;

import java.util.Iterator;

/**
 * Created by master on 2017/7/13.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);

    public abstract int numChildren(); // 子节点的个数

    public abstract Iterator<ASTree> children();

    public abstract String location();

    @Override
    public Iterator<ASTree> iterator() {
        return children();
    }
}
