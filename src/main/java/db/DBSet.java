package db;

import java.util.List;

@SuppressWarnings("unused")
public interface DBSet<T>
{
    T get(String id);
    List<T> getALl();
    void save(T t);
    boolean exists(String id);
}
