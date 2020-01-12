package yummy.demo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/9/30 13:40
 * @description：common dao interface
 */

public interface BaseDao<T> {
    public Serializable add(T t);

    public void delete(T t);

    public void deleteById(Serializable id);

    public T get(Serializable id);

    public void update(T t);

    public List<T> getAll();

    public T getUniqueResultByHQL(String hql, Object... values);

    public List<T> getListByHQL(String hql, Object... values);
}
