package yummy.old.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yummy.old.model.Customer;



@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    BaseDao baseDao;
    public Customer findById(int id){
        return (Customer) baseDao.load(Customer.class,id);
    }

    public void add(Customer cst){
        baseDao.save(cst);
    }

    public void update(Customer cst){
        baseDao.update(cst);
    }

    public void delete(int id){
        baseDao.delete(Customer.class,id);
    }


    public Customer login(String email,String password){
        Session session=baseDao.getSession();
        Customer cst=null;
        String hql="SELECT c FROM Customer c WHERE c.email = ?1 and c.password=?2";
        Query query = session.createQuery(hql);
        query.setParameter(1, email);
        query.setParameter(2, password);
        cst=(Customer)query.uniqueResult();
        session.close();
        return cst;
    }

    public Customer findByEmail(String email){
        Session session=baseDao.getSession();
        Customer cst=null;
        String hql="SELECT c FROM Customer c WHERE c.email = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, email);
        cst=(Customer)query.uniqueResult();
        session.close();
        return cst;
    }

    @Override
    public void setActive(int id) {
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            Customer cst=session.get(Customer.class,id);
            if(cst==null) return;
            cst.setIsActive(true);
            session.update(cst);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }
}
