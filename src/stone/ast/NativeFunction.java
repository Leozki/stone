package stone.ast;

import stone.exception.StoneException;

import java.lang.reflect.Method;

/**
 * Created by master on 2017/7/20.
 */
public class NativeFunction {

    protected String name;
    protected Method method;
    protected int numParams;

    public NativeFunction(String name, Method method) {
        this.name = name;
        this.method = method;
        numParams = method.getParameterTypes().length;
    }

    @Override
    public String toString() {
        return "<native:" + hashCode() + ">";
    }

    public int numOfParameters() {
        return numParams;
    }

    public Object invoke(Object[] args, ASTree tree) {
        try {
            return method.invoke(null, args);
        } catch (Exception e) {
            throw new StoneException("bad native function call: " + name, tree);
        }
    }
}
