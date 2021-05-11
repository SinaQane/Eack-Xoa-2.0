package db;

import java.util.List;

public interface DBSet<T>
{
    T get(String id);
    List<T> getALl();
    void save(T t);
    boolean exists(String id);
}
