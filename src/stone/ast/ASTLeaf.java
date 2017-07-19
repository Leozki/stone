package stone.ast;

import stone.env.Environment;
import stone.exception.StoneException;
import stone.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by master on 2017/7/14.
 */
public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<>();
    protected Token token; // 叶节点才有token字段

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token token() {
        return token;
    }

    @Override
    public Object eval(Environment env) {
        throw new StoneException("cannot eval: " + toString(), this);
    }
}
