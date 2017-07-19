package stone.env;

/**
 * Created by master on 2017/7/18.
 */
public interface Environment {
    void put(String name, Object value);

    Object get(String name);

    public void putNew(String name, Object value);

    public Environment where(String name);

    void setOuter(Environment e);
}
