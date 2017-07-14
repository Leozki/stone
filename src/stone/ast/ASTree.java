package stone.ast;

/**
 * Created by master on 2017/7/13.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);

    public abstract int numChildren(); // 子节点的个数

    public abstract Iterable<ASTree> children();

    public abstract String location();

    public Iterable<ASTree> iterable() {
        return children();
    }
}
