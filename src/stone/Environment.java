package stone;

/**
 * Created by master on 2017/7/18.
 */
public interface Environment {
    void put(String name, Object value);

    Object get(String name);

}
