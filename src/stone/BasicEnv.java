package stone;

import java.util.HashMap;

/**
 * Created by master on 2017/7/18.
 */
public class BasicEnv implements Environment {

    // hash表的键不是变量本身，是变量的名称
    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<>();
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }
}
