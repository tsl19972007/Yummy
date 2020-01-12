package yummy.old.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class BaseDaoImpl implements BaseDao {

    /**
     * Autowired 自动装配 相当于get() set()
     */
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return
     */
    public Session getSession() {
        //return sessionFactory.getCurrentSession();
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * 根据 id 查询信息 * * @param id * @return
     */
    @SuppressWarnings("rawtypes")
    public Object load(Class c, int id) {
        Session session = getSession();
        return session.get(c, id);
    }

    /**
     * 获取所有信息 * * @param c * * @return
     */

    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        return session.createQuery(hql).list();

    }

    /**
     * 获取总数量 * * @param c * @return
     */

    public Long getTotalCount(Class c) {
        Session session = getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 保存（作为完整事务） * * @param action *
     */
    public Object save(Object bean) {
        Object id=null;
        Session session = getSession();
        try{
            session.beginTransaction();
            id=session.save(bean);
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    /**
     * 更新（作为完整事务） * * @param action *
     */
    public void update(Object bean) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(bean);
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    /**
     * 删除 * * @param action *
     */
    public void delete(Object bean) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.delete(bean);
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    /**
     * 根据ID删除（作为完整事务） * * @param c 类 * * @param id ID *
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Class c, int id) {
        Session session = getSession();
        try{
            session.beginTransaction();
            Object obj = session.get(c, id);
            session.delete(obj);
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    /**
     * 批量删除（作为完整事务） * * @param c 类 * * @param ids ID 集合 *
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Class c, int[] ids) {
        Session session = getSession();
        try{
            session.beginTransaction();
            for (int id : ids) {
                Object obj = getSession().get(c, id);
                if (obj != null) {
                    getSession().delete(obj);
                }
            }
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

}