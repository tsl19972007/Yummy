package yummy.demo.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yummy.demo.model.Manager;
import yummy.demo.model.Menu;
import yummy.demo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {
    @Autowired
    BaseDao baseDao;

    public List<Restaurant> getAllRestaurants(){
        List list=baseDao.getAllList(Restaurant.class);
        List<Restaurant> rstList=new ArrayList<Restaurant>();
        for(int i=0;i<list.size();i++){
            rstList.add((Restaurant) list.get(i));
        }
        return rstList;
    }

    @Override
    public Restaurant findById(int id) {
        return (Restaurant) baseDao.load(Restaurant.class,id);
    }

    @Override
    public int add(Restaurant rst) {
        return (Integer)baseDao.save(rst);
    }

    @Override
    public void updateRst(Restaurant rst) {
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            String hql = "UPDATE Restaurant r SET r.password=?2,r.type=?3,r.name=?4,r.phone=?5,r.address=?6 WHERE r.id = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1, rst.getId());
            query.setParameter(2, rst.getPassword());
            query.setParameter(3, rst.getType());
            query.setParameter(4, rst.getName());
            query.setParameter(5, rst.getPhone());
            query.setParameter(6, rst.getAddress());
            int ret = query.executeUpdate();
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    @Override
    public void updateMenu(int rstId, Menu menu){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            Restaurant rst=session.get(Restaurant.class,rstId);
            rst.setMenu(menu);
            session.update(rst);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    @Override
    public void delete(int id) {
        baseDao.delete(Restaurant.class,id);
    }

    @Override
    public Restaurant login(int id, String password) {
        Session session=baseDao.getSession();
        Restaurant rst=null;
        String hql="SELECT r FROM Restaurant r WHERE r.id = ?1 and r.password=?2";
        Query query = session.createQuery(hql);
        query.setParameter(1, id);
        query.setParameter(2, password);
        rst=(Restaurant) query.uniqueResult();
        session.close();
        return rst;
    }

    public void balance(int id){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            Restaurant rst=(Restaurant)session.get(Restaurant.class,id);
            double profit=rst.getProfit();
            rst.setProfit(0);
            rst.setBalance(rst.getBalance()+profit);
            session.update(rst);
            Manager mng=(Manager)session.get(Manager.class,Manager.getDefaultId());
            mng.setBalance(mng.getBalance()-profit);
            session.update(mng);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void balanceAll(){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            String hql = "SELECT r FROM Restaurant r WHERE r.profit>0 ";
            List rstList=session.createQuery(hql).list();
            double totalProfit=0;
            for(Object obj :rstList){
                Restaurant rst = (Restaurant) obj;
                rst.setBalance(rst.getBalance()+rst.getProfit());
                totalProfit+=rst.getProfit();
                rst.setProfit(0);
                session.update(rst);
            }
            String hqlUpd = "UPDATE Manager m SET m.balance=m.balance- ?1 ";
            Query query = session.createQuery(hqlUpd);
            query.setParameter(1, totalProfit);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }
}
