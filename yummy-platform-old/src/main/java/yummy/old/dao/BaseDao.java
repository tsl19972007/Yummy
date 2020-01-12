package yummy.old.dao;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao {

    public Session getSession();

    public void flush();

    public void clear();

    public Object load(Class c, int id);

    public List getAllList(Class c);

    public Long getTotalCount(Class c);

    public Object save(Object bean);

    public void update(Object bean);

    public void delete(Object bean);

    public void delete(Class c, int id);

    public void delete(Class c, int[] ids);

}