package yummy.demo.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BaseDaoImpl implements BaseDao {

    /**
     * Autowired 自动装配 相当于get() set()
     */
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;
    /**
     * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return
     */
    public Session getSession() {
        return entityManager.unwrap(Session.class);
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
    public Object load(Class c, int id) {
        return getSession().get(c, id);
    }

    /**
     * 获取所有信息 * * @param c * * @return
     */

    public List getAllList (Class c){
        Session session = getSession();
        String hql = "from " + c.getName();
        List list = null;
        list = session.createQuery(hql).list();
        return list;
    }

    /**
     * 获取总数量 * * @param c * @return
     */

    public Long getTotalCount (Class c){
        Session session = getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 保存（作为完整事务） * * @param action *
     */
    public Object save (Object bean){
        return getSession().save(bean);
    }

    /**
     * 更新（作为完整事务） * * @param action *
     */
    public void update (Object bean){
        getSession().update(bean);
    }

    /**
     * 删除 * * @param action *
     */
    public void delete (Object bean){
        getSession().delete(bean);
    }

    /**
     * 根据ID删除（作为完整事务） * * @param c 类 * * @param id ID *
     */
    @SuppressWarnings({"rawtypes"})
    public void delete (Class c,int id){
        Session session = getSession();
        Object obj = session.get(c, id);
        session.delete(obj);
    }

    /**
     * 批量删除（作为完整事务） * * @param c 类 * * @param ids ID 集合 *
     */
    public void delete (Class c,int[] ids){
        Session session = getSession();
        for (int id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }
}