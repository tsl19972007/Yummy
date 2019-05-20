package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.CustomerDao;
import yummy.demo.dao.OrderDao;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Customer;
import yummy.demo.model.Order;
import yummy.demo.model.Restaurant;
import yummy.demo.statistics.CustomerStatistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static yummy.demo.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    CustomerDao cstDao;
    @Autowired
    RestaurantDao rstDao;

    @Override
    public Order findById(int id) {
        Order order=orderDao.findById(id);
        order.setRstId(order.getRstId()+ID_DIFF);
        return order;
    }

    @Override
    public List<Order> findByCst(int cstId) {
        orderDao.cancelOrders();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(Order order:orderList){
            order.setRstId(order.getRstId()+ID_DIFF);
        }
        return orderList;
    }

    @Override
    public List<Order> findUnpaidByCst(int cstId) {
        orderDao.cancelOrders();
        List<Order> orderUnpaidList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(Order order:orderList){
            if(order.getState().equals("待支付")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderUnpaidList.add(order);
            }
        }
        return orderUnpaidList;
    }

    @Override
    public List<Order> findPaidByCst(int cstId) {
        List<Order> orderPaidList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(Order order:orderList){
            if(order.getState().equals("进行中")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderPaidList.add(order);
            }
        }
        return orderPaidList;
    }

    @Override
    public List<Order> findCompletedByCst(int cstId) {
        List<Order> orderCompletedList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(Order order:orderList){
            if(order.getState().equals("已完成")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderCompletedList.add(order);
            }
        }
        return orderCompletedList;
    }

    @Override
    public List<Order> findReturnedByCst(int cstId) {
        List<Order> orderReturnedList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(Order order:orderList){
            if(order.getState().equals("已退订")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderReturnedList.add(order);
            }
        }
        return orderReturnedList;
    }

    @Override
    public int add(Order order) {
        order.setRstId(order.getRstId()-ID_DIFF);
        return orderDao.add(order);
    }

    @Override
    public void cancelOrder(int id) {
        orderDao.delete(id);
    }

    @Override
    public double returnOrder(int id) {
        return orderDao.returnOrder(id);
    }

    @Override
    public boolean payOrder(int id) {
        return orderDao.payOrder(id);
    }

    public void finishOrder(int id){
        orderDao.finishOrder(id);
    }

    @Override
    public double getDiscount(int cstId, double totalPrice) {
        double discount=0;
        Customer cst=cstDao.findById(cstId);
        if(cst.getLevel().equals("会员")){
            discount=totalPrice*0.05;
        }else if(cst.getLevel().equals("超级会员")){
            discount=totalPrice*0.1;
        }else if(cst.getLevel().equals("顶级会员")){
            discount=totalPrice*0.2;
        }
        return Double.parseDouble(String.format("%.2f", discount));
    }

    @Override
    public CustomerStatistics getCustomerStatistics(int cstId) {
        Customer cst=cstDao.findById(cstId);
        String nextLevel=cst.getNextLevel();
        double consumptionToNextLevel=cst.getConsumptionToNextLevel();
        double totalConsumption=cst.getConsumption();
        List<Order> orderList=orderDao.findByCst(cstId);
        int totalOrderNum=0;
        int todayOrderNum=0;
        double todayConsumption=0;
        ArrayList<Integer> rstIdList=new ArrayList<Integer>();
        ArrayList<String> rstNameList=new ArrayList<String>();
        ArrayList<Double> rstConsumptionList=new ArrayList<Double>();
        for(Order order:orderList){
            if(order.getState().equals("已退订")||order.getState().equals("待支付")){
                continue;
            }
            totalOrderNum++;
            Date orderTime=order.getOrderTime();
            Date now=new Date();
            if(orderTime.getYear()==now.getYear()&&orderTime.getMonth()==now.getMonth()&&orderTime.getDate()==now.getDate()){
                todayOrderNum++;
                todayConsumption+=order.getConsumption();
            }
            if(!rstIdList.contains(order.getRstId())){
                rstIdList.add(order.getRstId());
                rstConsumptionList.add(order.getConsumption());
            }else{
                int index=rstIdList.indexOf(order.getRstId());
                double consumptionTemp=rstConsumptionList.get(index);
                rstConsumptionList.set(index,consumptionTemp+order.getConsumption());
            }
        }

        for(int i=0;i<rstConsumptionList.size()-1;i++) {
            for (int j=0;j<rstConsumptionList.size()-i-1;j++) {
                if(rstConsumptionList.get(j)<rstConsumptionList.get(j+1)){
                    double tempConsumption=rstConsumptionList.get(j);
                    rstConsumptionList.set(j,rstConsumptionList.get(j+1));
                    rstConsumptionList.set(j+1,tempConsumption);
                    int tempId=rstIdList.get(j);
                    rstIdList.set(j,rstIdList.get(j+1));
                    rstIdList.set(j+1,tempId);
                }
            }
        }
        for(int id:rstIdList){
            rstNameList.add(rstDao.findById(id).getName());
        }

        CustomerStatistics cstStatistics=new CustomerStatistics(totalOrderNum,todayOrderNum,totalConsumption, todayConsumption,nextLevel,consumptionToNextLevel,rstNameList, rstConsumptionList) ;
        return cstStatistics;
    }
}
