package yummy.demo.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yummy.demo.exception.BusinessException;
import yummy.demo.exception.BusinessExceptionEnum;
import yummy.demo.model.*;

import java.util.ArrayList;
import java.util.Calendar;
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
        String hql = "SELECT o.id FROM Order o WHERE o.orderTime < ?1 AND o.state = '待支付'";
        Date date=new Date(System.currentTimeMillis()-payInterval);
        Query query = session.createQuery(hql);
        query.setParameter(1,date);
        List idList= query.getResultList();
        for (Object id : idList) {
            delete((Integer)id);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List list=baseDao.getAllList(Order.class);
        List<Order> orderList=new ArrayList<>();
        for(Object obj:list){
            Order order=(Order)obj;
            if(!order.getState().equals("待支付")) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> getAnnualOrders() {
        Date now=new Date();
        List list=baseDao.getAllList(Order.class);
        List<Order> orderList=new ArrayList<>();
        for(Object obj:list){
            Order order=(Order)obj;
            if(order.getOrderTime().getYear()==now.getYear()&&!order.getState().equals("待支付")) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> getMonthlyOrders() {
        Date now=new Date();
        List list=baseDao.getAllList(Order.class);
        List<Order> orderList=new ArrayList<>();
        for(Object obj:list){
            Order order=(Order)obj;
            if(order.getOrderTime().getYear()==now.getYear()&&order.getOrderTime().getMonth()==now.getMonth()&&!order.getState().equals("待支付")) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> getWeeklyOrders() {
        Date now=new Date();
        List list=baseDao.getAllList(Order.class);
        List<Order> orderList=new ArrayList<>();
        for(Object obj:list){
            Order order=(Order)obj;
            Calendar nowCal=Calendar.getInstance();
            Calendar dateCal=Calendar.getInstance();
            nowCal.setTime(now);
            dateCal.setTime(order.getOrderTime());
            nowCal.setFirstDayOfWeek(Calendar.MONDAY);
            dateCal.setFirstDayOfWeek(Calendar.MONDAY);
            if(nowCal.get(Calendar.WEEK_OF_YEAR)==dateCal.get(Calendar.WEEK_OF_YEAR)&&order.getOrderTime().getYear()==now.getYear()&&!order.getState().equals("待支付")) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    public List<Order> findByCst(int cstId){
        Session session=baseDao.getSession();
        String hql="SELECT o FROM Order o WHERE o.cstId = ?1";
        Query query = session.createQuery(hql,Order.class);
        query.setParameter(1, cstId);
        List<Order> orderList=query.getResultList();
        return orderList;
    }

    public List<Order> findByRst(int rstId){
        Session session=baseDao.getSession();
        List<Order> orderList;
        String hql="SELECT o FROM Order o WHERE o.rstId = ?1";
        Query query = session.createQuery(hql,Order.class);
        query.setParameter(1, rstId);
        orderList=query.getResultList();
        return orderList;
    }

    public int add(Order order){
        Session session = baseDao.getSession();
        int id=0;
        for(OrderItem orderItem:order.getItemList()){
            MenuItem menuItem=session.get(MenuItem.class,orderItem.getMenuItemId());
            if(menuItem.getNum()<orderItem.getNum()){
                id=-1;
                throw new BusinessException(BusinessExceptionEnum.OUT_OF_STOCK);
            }
            menuItem.setNum(menuItem.getNum()-orderItem.getNum());
            session.update(menuItem);
        }
        id = (Integer) session.save(order);
        return id;
    }

    public boolean payOrder(int id){
        Session session=baseDao.getSession();
        Order order=session.get(Order.class,id);
        Customer cst=session.get(Customer.class,order.getCstId());
        if(cst.getBalance()<order.getConsumption()){
            throw new BusinessException(BusinessExceptionEnum.OUT_OF_BALANCE);
        }
        cst.setBalance(cst.getBalance()-order.getConsumption());
        session.update(cst);
        order.setState("进行中");
        session.update(order);
        Manager manager=session.get(Manager.class, Manager.getDefaultId());
        manager.setBalance(manager.getBalance()+order.getConsumption());
        session.update(manager);
        return true;
    }

    public void finishOrder(int id){
        Session session=baseDao.getSession();
        Order order=session.get(Order.class,id);
        order.setState("已完成");
        session.update(order);

        Restaurant rst=session.get(Restaurant.class,order.getRstId());
        rst.setProfit(order.getConsumption()+rst.getProfit());
        session.update(rst);

        Customer cst=session.get(Customer.class,order.getCstId());
        cst.setConsumption(order.getConsumption()+cst.getConsumption());
        session.update(cst);
    }


    public double returnOrder(int id){
        Session session=baseDao.getSession();
        double returned=0;
        Order order=session.get(Order.class,id);
        order.setState("已退订");
        session.update(order);

        returned=getReturnedMoney(order.getConsumption(),order.getOrderTime(),order.getArriveTime());

        Restaurant rst=session.get(Restaurant.class,order.getRstId());
        rst.setProfit(rst.getProfit() + order.getConsumption() - returned);
        session.update(rst);

        Manager manager=session.get(Manager.class, Manager.getDefaultId());
        manager.setBalance(manager.getBalance() - returned);
        session.update(manager);

        Customer cst=session.get(Customer.class,order.getCstId());
        cst.setBalance(cst.getBalance() + returned);
        session.update(cst);
        return returned;
    }

    public void delete(int id){
        Session session = baseDao.getSession();
        Order order = session.get(Order.class, id);
        session.delete(order);
        for(OrderItem orderItem:order.getItemList()){
            MenuItem menuItem=session.get(MenuItem.class,orderItem.getMenuItemId());
            menuItem.setNum(menuItem.getNum()+orderItem.getNum());
            session.update(menuItem);
        }
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
