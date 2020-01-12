package yummy.demo.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yummy.demo.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    BaseDao baseDao;

    private static final long payInterval =  2 * 60 * 1000;

    public Order findById(int id){ return (Order)baseDao.load(Order.class,id); }

    public void cancelOrders(){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            String hql = "SELECT o.id FROM Order o WHERE o.orderTime < ?1 AND o.state = 'unpaid'";
            Date date=new Date(System.currentTimeMillis()-2*60*1000);
            Query query = session.createQuery(hql);
            query.setParameter(1,date);
            List idList= query.getResultList();
            for (Object id : idList) {
                Order order = session.get(Order.class, (Integer)id);
                if (order != null) {
                    session.delete(order);
                }
            }
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public List<Order> findByCst(int cstId){
        Session session=baseDao.getSession();
        String hql="SELECT o FROM Order o WHERE o.cstId = ?1";
        Query query = session.createQuery(hql,Order.class);
        query.setParameter(1, cstId);
        List<Order> orderList=query.getResultList();
        session.close();
        return orderList;
    }

    public List<Order> findByRst(int rstId){
        Session session=baseDao.getSession();
        List<Order> orderList;
        String hql="SELECT o FROM Order o WHERE o.rstId = ?1";
        Query query = session.createQuery(hql,Order.class);
        query.setParameter(1, rstId);
        orderList=query.getResultList();
        session.close();
        return orderList;
    }

    public int add(Order order){
        Session session = baseDao.getSession();
        int id=0;
        try{
            session.beginTransaction();
            for(OrderItem orderItem:order.getItemList()){
                MenuItem menuItem=session.get(MenuItem.class,orderItem.getMenuItemId());
                if(menuItem.getNum()<orderItem.getNum()){
                    id=-1;
                    throw new Exception("库存不足");
                }
                menuItem.setNum(menuItem.getNum()-orderItem.getNum());
                session.update(menuItem);
            }
            id = (Integer) session.save(order);
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    public boolean payOrder(int id){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            Order order=session.get(Order.class,id);
            Customer cst=session.get(Customer.class,order.getCstId());
            if(cst.getBalance()<order.getTotal()){
                throw new Exception("余额不足");
            }
            cst.setBalance(cst.getBalance()-order.getTotal());
            session.update(cst);
            order.setState("paid");
            session.update(order);
            Manager manager=session.get(Manager.class, Manager.getDefaultId());
            manager.setBalance(manager.getBalance()+order.getTotal());
            session.update(manager);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        session.close();
        return true;
    }

    public void finishOrder(int id){
        Session session=baseDao.getSession();
        try {
            session.beginTransaction();
            Order order=session.get(Order.class,id);
            order.setState("completed");
            session.update(order);

            Restaurant rst=session.get(Restaurant.class,order.getRstId());
            rst.setProfit(order.getTotal()+rst.getProfit());
            session.update(rst);

            Customer cst=session.get(Customer.class,order.getCstId());
            cst.setConsumption(order.getTotal()+cst.getConsumption());
            session.update(cst);

            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }


    public double returnOrder(int id){
        Session session=baseDao.getSession();
        double returned=0;
        try {
            session.beginTransaction();
            Order order=session.get(Order.class,id);
            order.setState("returned");
            session.update(order);

            returned=getReturnedMoney(order.getTotal(),order.getOrderTime(),order.getArriveTime());

            Restaurant rst=session.get(Restaurant.class,order.getRstId());
            rst.setProfit(rst.getProfit() + order.getTotal() - returned);
            session.update(rst);

            Manager manager=session.get(Manager.class, Manager.getDefaultId());
            manager.setBalance(manager.getBalance() - returned);
            session.update(manager);

            Customer cst=session.get(Customer.class,order.getCstId());
            cst.setBalance(cst.getBalance() + returned);
            session.update(cst);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return returned;
    }

    public void delete(int id){
        Session session = baseDao.getSession();
        try{
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.delete(order);
            for(OrderItem orderItem:order.getItemList()){
                MenuItem menuItem=session.get(MenuItem.class,orderItem.getMenuItemId());
                menuItem.setNum(menuItem.getNum()+orderItem.getNum());
                session.update(menuItem);
            }
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void update(Order order){
        baseDao.update(order);
    }

    private double getReturnedMoney(double total,Date orderTime,Date arriveTime){
        Date now=new Date();
        if(now.after(arriveTime)) return 0;
        long diff1=arriveTime.getTime()-now.getTime();
        long diff=arriveTime.getTime()-orderTime.getTime();
        double ratio=1.0 * diff1/diff;
        return Double.parseDouble(String.format("%.2f", total*ratio));
    }
}
