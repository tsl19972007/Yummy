package yummy.demo.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import yummy.demo.model.Order;

import java.util.Date;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 16:03
 * @description：implementation of orderDao
 */

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    private static final long payInterval =  2 * 60 * 1000;

    @Override
    public List<Order> getCompletedAnnualOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE year(o.order_time) = year(now())");
    }

    @Override
    public List<Order> getCompletedMonthlyOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE month(o.order_time) = month(now())");
    }

    @Override
    public List<Order> getCompletedWeeklyOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE yearweek(o.order_time) = yearweek(now())");
    }

    @Override
    public List<Order> findByCst(int cstId) {
        return getListByHQL("SELECT o FROM Order o WHERE o.cstId = ?0",cstId);
    }

    @Override
    public List<Order> findByCst(int cstId,String state) {
        return getListByHQL("SELECT o FROM Order o WHERE o.cstId = ?0 AND o.state=?1",cstId,state);
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return getListByHQL("SELECT o FROM Order o WHERE o.rstId = ?0",rstId);
    }

    @Override
    public List<Order> findByRst(int rstId, String state) {
        return getListByHQL("SELECT o FROM Order o WHERE o.rstId = ?0 AND o.state=?1",rstId,state);
    }

    private void cancelByCst(int cstId){
        Query query=getSession().createQuery("DELETE FROM Order o WHERE o.orderTime<?0 AND o.state='待支付' AND o.cstId=?1");
        Date date=new Date(System.currentTimeMillis()-payInterval);
        query.setParameter(0,date);
        query.setParameter(1,cstId);
        query.executeUpdate();
    }

    private void cancelByRst(int rstId){
        Query query=getSession().createQuery("DELETE FROM Order o WHERE o.orderTime<?0 AND o.state='待支付' AND o.rstId=?1");
        Date date=new Date(System.currentTimeMillis()-payInterval);
        query.setParameter(0,date);
        query.setParameter(1,rstId);
        query.executeUpdate();
    }

    private void cancel(){
        Query query=getSession().createQuery("DELETE FROM Order o WHERE o.orderTime<?0 AND o.state='待支付'");
        Date date=new Date(System.currentTimeMillis()-payInterval);
        query.setParameter(0,date);
        query.executeUpdate();
    }
}
