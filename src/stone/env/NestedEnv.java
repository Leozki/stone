package stone.env;

import java.util.HashMap;

/**
 * Created by master on 2017/7/19.
 */
public class NestedEnv implements Environment {
    protected HashMap<String, Object> values;
    protected Environment outer; // 外层作用域对应的环境

    public NestedEnv() {
        this(null);
    }

    public NestedEnv(Environment env) {
        values = new HashMap<>();
        outer = env;
    }

    public void setOuter(Environment outer) {
        this.outer = outer;
    }

    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null) {
            return outer.get(name);
        } else {
            return v;
        }
    }

    /**
     * 赋值时不考虑外部作用域
     *
     * @param name
     * @param value
     */
    @Override
    public void putNew(String name, Object value) {
        values.put(name, value);
    }

    /**
     * 先判断外部作用域是否存在该变量,如果有则赋值给外部作用域中的变量
     *
     * @param name
     * @param value
     */
    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        e.putNew(name, value);
    }

    @Override
    public Environment where(String name) {
        if (values.get(name) != null)
            return this;
        else if (outer == null)
            return null;
        else
            return outer.where(name);
    }
}
