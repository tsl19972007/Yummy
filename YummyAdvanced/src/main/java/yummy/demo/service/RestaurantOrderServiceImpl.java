package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.CustomerDao;
import yummy.demo.dao.OrderDao;
import yummy.demo.model.Order;
import yummy.demo.statistics.RestaurantStatistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static yummy.demo.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class RestaurantOrderServiceImpl implements RestaurantOrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    CustomerDao cstDao;


    @Override
    public Order findById(int id) {
        Order order=orderDao.findById(id);
        order.setRstId(order.getRstId()+ID_DIFF);
        return order;
    }

    @Override
    public List<Order> findByRst(int rstId) {
        List<Order> orderList=orderDao.findByRst(rstId-RestaurantServiceImpl.ID_DIFF);
        for(int i=0;i<orderList.size();i++){
            orderList.get(i).setRstId(orderList.get(i).getRstId()+ID_DIFF);
        }
        return orderList;
    }

    @Override
    public List<Order> findPaidByRst(int rstId) {
        List<Order> orderPaidList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(rstId-RestaurantServiceImpl.ID_DIFF);
        for(Order order:orderList){
            if(order.getState().equals("进行中")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderPaidList.add(order);
            }
        }
        return orderPaidList;
    }

    @Override
    public List<Order> findCompletedByRst(int rstId) {
        List<Order> orderCompletedList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(rstId-RestaurantServiceImpl.ID_DIFF);
        for(Order order:orderList){
            if(order.getState().equals("已完成")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderCompletedList.add(order);
            }
        }
        return orderCompletedList;
    }


    @Override
    public List<Order> findReturnedByRst(int rstId) {
        List<Order> orderReturnedList=new ArrayList<Order>();
        List<Order> orderList=orderDao.findByCst(rstId-RestaurantServiceImpl.ID_DIFF);
        for(Order order:orderList){
            if(order.getState().equals("已退订")) {
                order.setRstId(order.getRstId()+ID_DIFF);
                orderReturnedList.add(order);
            }
        }
        return orderReturnedList;
    }

    @Override
    public void finishOrder(int id) {
        orderDao.finishOrder(id);
    }

    @Override
    public RestaurantStatistics getRestaurantStatistics(int rstId) {
        List<Order> orderList=orderDao.findByRst(rstId-RestaurantServiceImpl.ID_DIFF);
        ArrayList<Integer> cstIdList = new ArrayList<Integer>();
        ArrayList<String> cstNameList=new ArrayList<String>();
        ArrayList<Double> cstConsumptionList=new ArrayList<Double>();
        int totalOrderNum=0;
        int todayOrderNum=0;
        double totalProfit=0;
        double todayProfit=0;
        ArrayList<Double> weekProfitList=new ArrayList<Double>();
        Calendar cal=Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek==0){
            dayOfWeek=7;
        }
        for(int i=0;i<dayOfWeek;i++){
            weekProfitList.add(0.0);
        }
        for(Order order:orderList){
            if(order.getState().equals("已退订")||order.getState().equals("待支付")){
                continue;
            }
            totalOrderNum++;
            totalProfit+=order.getConsumption()*ManagerServiceImpl.PROFIT_RATIO;
            Date orderTime=order.getOrderTime();
            Date now=new Date();
            if(orderTime.getYear()==now.getYear()&&orderTime.getMonth()==now.getMonth()&&orderTime.getDate()==now.getDate()){
                todayOrderNum++;
                todayProfit+=order.getConsumption()*ManagerServiceImpl.PROFIT_RATIO;
            }
            if(!cstIdList.contains(order.getCstId())){
                cstIdList.add(order.getCstId());
                cstConsumptionList.add(order.getConsumption());
            }else{
                int index=cstIdList.indexOf(order.getCstId());
                double consumptionTemp=cstConsumptionList.get(index);
                cstConsumptionList.set(index,consumptionTemp+order.getConsumption());
            }
            Calendar nowCal=Calendar.getInstance();
            Calendar dateCal=Calendar.getInstance();
            nowCal.setTime(now);
            dateCal.setTime(order.getOrderTime());
            nowCal.setFirstDayOfWeek(Calendar.MONDAY);
            dateCal.setFirstDayOfWeek(Calendar.MONDAY);
            if(nowCal.get(Calendar.WEEK_OF_YEAR)==dateCal.get(Calendar.WEEK_OF_YEAR)&&order.getOrderTime().getYear()==now.getYear()) {
                int day=dateCal.get(Calendar.DAY_OF_WEEK)-1;
                if(day==0){
                    day=7;
                }
                day--;
                weekProfitList.set(day,weekProfitList.get(day)+order.getConsumption()*ManagerServiceImpl.PROFIT_RATIO);
            }
        }

        for(int i=0;i<cstConsumptionList.size()-1;i++) {
            for (int j=0;j<cstConsumptionList.size()-i-1;j++) {
                if(cstConsumptionList.get(j)<cstConsumptionList.get(j+1)){
                    double tempConsumption=cstConsumptionList.get(j);
                    cstConsumptionList.set(j,cstConsumptionList.get(j+1));
                    cstConsumptionList.set(j+1,tempConsumption);
                    int tempId=cstIdList.get(j);
                    cstIdList.set(j,cstIdList.get(j+1));
                    cstIdList.set(j+1,tempId);
                }
            }
        }
        for(int id:cstIdList){
            cstNameList.add(cstDao.findById(id).getName());
        }

        RestaurantStatistics rstStatistics=new RestaurantStatistics(cstNameList, cstConsumptionList, totalOrderNum, todayOrderNum, totalProfit, todayProfit, weekProfitList);
        return rstStatistics;
    }
}
