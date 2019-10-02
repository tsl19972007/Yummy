package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.dao.CustomerDao;
import yummy.demo.dao.ManagerDao;
import yummy.demo.dao.OrderDao;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.*;

import java.util.Date;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:27
 * @description：implementation of orderService
 */

@Transactional
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDao orderDao;
    @Autowired
    CustomerDao cstDao;
    @Autowired
    RestaurantDao rstDao;
    @Autowired
    ManagerDao managerDao;

    @Override
    public Order findById(int id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> findByCst(int cstId) {
        return orderDao.findByCst(cstId);
    }

    @Override
    public List<Order> findByCst(int cstId,String state) {
        return orderDao.findByCst(cstId,state);
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return orderDao.findByRst(rstId);
    }

    @Override
    public List<Order> findByRst(int rstId,String state) {
        return orderDao.findByRst(rstId,state);
    }

    @Override
    public int add(Order order) {
        //更新餐厅菜单信息
        for(OrderItem orderItem:order.getItemList()){
            MenuItem menuItem=rstDao.getMenuItem(orderItem.getMenuItemId());
            if(menuItem.getNum()<orderItem.getNum()){
                return -1;
            }
            menuItem.setNum(menuItem.getNum()-orderItem.getNum());
            rstDao.updateMenuItem(menuItem);
        }
        //更新订单信息：状态为待支付
        return (int)orderDao.add(order);
    }

    public boolean payOrder(int id){
        //更新订单信息：状态为进行中
        Order order=findById(id);
        order.setState("进行中");
        orderDao.update(order);
        //更新顾客信息:顾客余额减少
        Customer cst=cstDao.get(order.getCstId());
        if(cst.getBalance()<order.getConsumption()){
            return false;
        }
        cst.setBalance(cst.getBalance()-order.getConsumption());
        cstDao.update(cst);
        //更新管理员信息：管理员余额增加
        Manager manager=managerDao.get(Manager.getDefaultId());
        manager.setBalance(manager.getBalance()+order.getConsumption());
        managerDao.update(manager);
        return true;
    }

    public void finishOrder(int id){
        //更新订单信息：状态为已完成
        Order order=findById(id);
        order.setState("已完成");
        orderDao.update(order);
        //更新餐厅信息：餐厅盈利增加
        Restaurant rst=rstDao.get(order.getRstId());
        rst.setProfit(order.getConsumption()+rst.getProfit());
        rstDao.update(rst);
        //更新顾客信息：顾客消费增加
        Customer cst=cstDao.get(order.getCstId());
        cst.setConsumption(order.getConsumption()+cst.getConsumption());
        cstDao.update(cst);
    }

    public double returnOrder(int id){
        //更新订单信息:状态为已退订
        Order order=orderDao.get(id);
        order.setState("已退订");
        orderDao.update(order);
        //更新餐厅信息：餐厅盈利增加（增加退订损失的那部分）
        double returned=getReturnedMoney(order.getConsumption(),order.getOrderTime(),order.getArriveTime());
        Restaurant rst=rstDao.get(order.getRstId());
        rst.setProfit(rst.getProfit() + order.getConsumption() - returned);
        //更新餐厅菜单信息
        for(OrderItem orderItem:order.getItemList()){
            MenuItem menuItem=rstDao.getMenuItem(orderItem.getMenuItemId());
            menuItem.setNum(menuItem.getNum()+orderItem.getNum());
            rstDao.updateMenuItem(menuItem);
        }
        rstDao.update(rst);

        //更新管理员信息：管理员余额减少（减少订单总额）
        Manager manager=managerDao.get(Manager.getDefaultId());
        manager.setBalance(manager.getBalance() - order.getConsumption());
        managerDao.update(manager);
        //更新顾客信息：顾客余额增加（增加退订的部分）
        Customer cst=cstDao.get(order.getCstId());
        cst.setBalance(cst.getBalance() + returned);
        cstDao.update(cst);
        return returned;
    }

    public void cancelOrder(int id){
        //更新餐厅菜单信息
        Order order=orderDao.get(id);
        for(OrderItem orderItem:order.getItemList()){
            MenuItem menuItem=rstDao.getMenuItem(orderItem.getMenuItemId());
            menuItem.setNum(menuItem.getNum()+orderItem.getNum());
            rstDao.updateMenuItem(menuItem);
        }
        orderDao.delete(order);
    }

    @Override
    public double getDiscount(int cstId, double totalPrice) {
        double discount=0;
        Customer cst=cstDao.get(cstId);
        if(cst.getLevel().equals("会员")){
            discount=totalPrice*0.05;
        }else if(cst.getLevel().equals("超级会员")){
            discount=totalPrice*0.1;
        }else if(cst.getLevel().equals("顶级会员")){
            discount=totalPrice*0.2;
        }
        return Double.parseDouble(String.format("%.2f", discount));
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
