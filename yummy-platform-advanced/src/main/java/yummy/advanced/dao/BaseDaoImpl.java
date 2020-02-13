package yummy.advanced.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/9/30 13:39
 * @description：implementation of baseDao
 */

@Repository
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

    /*
     * 用反射获取具体类型
     */
    public BaseDaoImpl() {
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazz = (Class<T>) type.getActualTypeArguments()[0];
        }
    }

    protected final Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Serializable add(T t) {
        return getSession().save(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public void deleteById(Serializable id) {
        Session session = getSession();
        Object obj = session.get(clazz, id);
        session.delete(obj);
    }

    @Override
    public T get(Serializable id) {
        return getSession().get(clazz, id);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public List<T> getAll() {
        String hql = "from " + clazz.getName();
        return getSession().createQuery(hql).list();
    }

    @Override
    public T getUniqueResultByHQL(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        if (values != null) {
            int count = values.length;
            for (int i = 0; i < count; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByHQL(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        if (values != null) {
            int count = values.length;
            for (int i = 0; i < count; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    @Override
    public void flush() {
        getSession().flush();
    }
}
