package db;

public interface DBSet<T>
{
    T get(String id);
    void save(T t);
    boolean exists(String id);
}
