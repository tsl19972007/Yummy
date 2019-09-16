package yummy.demo.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yummy.demo.model.Customer;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    BaseDao baseDao;

    public List<Customer> getAllCustomers(){
        List list=baseDao.getAllList(Customer.class);
        List<Customer> cstList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            cstList.add((Customer) list.get(i));
        }
        return cstList;
    }

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
        return cst;
    }

    public Customer findByEmail(String email){
        Session session=baseDao.getSession();
        Customer cst=null;
        String hql="SELECT c FROM Customer c WHERE c.email = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, email);
        cst=(Customer)query.uniqueResult();
        return cst;
    }

    @Override
    public void setActive(int id) {
        Session session=baseDao.getSession();
        Customer cst=session.get(Customer.class,id);
        if(cst==null) return;
        cst.setIsActive(true);
        cst.setBalance(100);
        session.update(cst);
    }

    @Override
    public void writeOff(int id) {
        Session session=baseDao.getSession();
        Customer cst=session.get(Customer.class,id);
        if(cst==null) return;
        cst.setIsWrittenOff(true);
        session.update(cst);
    }
}
