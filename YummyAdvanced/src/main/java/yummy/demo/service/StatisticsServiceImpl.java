package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.CustomerDao;
import yummy.demo.dao.ManagerDao;
import yummy.demo.dao.OrderDao;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Customer;
import yummy.demo.model.Order;
import yummy.demo.statistics.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:34
 * @description：implementation of statisticsService
 */

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    CustomerDao cstDao;
    @Autowired
    RestaurantDao rstDao;
    @Autowired
    ManagerDao managerDao;

    @Override
    public CustomerStatistics getCustomerStatistics(int cstId) {
        Customer cst=cstDao.get(cstId);
        String nextLevel=cst.getNextLevel();
        double consumptionToNextLevel=cst.getConsumptionToNextLevel();
        double totalConsumption=cst.getConsumption();
        List<Order> orderList=orderDao.findByCst(cstId);
        int totalOrderNum=0;
        int todayOrderNum=0;
        double todayConsumption=0;
        ArrayList<Integer> rstIdList=new ArrayList<>();
        ArrayList<String> rstNameList=new ArrayList<>();
        ArrayList<Double> rstConsumptionList=new ArrayList<>();
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
            rstNameList.add(rstDao.get(id).getName());
        }

        return new CustomerStatistics(totalOrderNum,todayOrderNum,totalConsumption, todayConsumption,nextLevel,consumptionToNextLevel,rstNameList, rstConsumptionList) ;
    }

    @Override
    public RestaurantStatistics getRestaurantStatistics(int rstId) {
        List<Order> orderList=orderDao.findByRst(rstId);
        ArrayList<Integer> cstIdList = new ArrayList<>();
        ArrayList<String> cstNameList=new ArrayList<>();
        ArrayList<Double> cstConsumptionList=new ArrayList<>();
        int totalOrderNum=0;
        int todayOrderNum=0;
        double totalProfit=0;
        double todayProfit=0;
        ArrayList<Double> weekProfitList=new ArrayList<>();
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
            cstNameList.add(cstDao.get(id).getName());
        }

        return new RestaurantStatistics(cstNameList, cstConsumptionList, totalOrderNum, todayOrderNum, totalProfit, todayProfit, weekProfitList);
    }

    @Override
    public YummyAnnualFinance getYummyAnnualFinance() {
        List<Order> orderList=orderDao.getCompletedAnnualOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<>();
        ArrayList<Integer> orderNumList=new ArrayList<>();
        ArrayList<Double> profitList=new ArrayList<>();
        int monthOfYear=Calendar.getInstance().get(Calendar.MONTH)+1;
        for(int i=0;i<monthOfYear;i++){
            transactionAmountList.add(0.0);
            orderNumList.add(0);
            profitList.add(0.0);
        }
        for (Order order:orderList) {
            totalTransactionAmount+=Double.parseDouble(String.format("%.2f",order.getConsumption()));
            totalProfit+=Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO)));
            int month=order.getOrderTime().getMonth();
            transactionAmountList.set(month,transactionAmountList.get(month)+Double.parseDouble(String.format("%.2f", order.getConsumption())));
            orderNumList.set(month,orderNumList.get(month)+1);
            profitList.set(month,profitList.get(month)+Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO))));
        }
        return new YummyAnnualFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
    }

    @Override
    public YummyMonthlyFinance getYummyMonthlyFinance() {
        List<Order> orderList=orderDao.getCompletedMonthlyOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<>();
        ArrayList<Integer> orderNumList=new ArrayList<>();
        ArrayList<Double> profitList=new ArrayList<>();
        int dayOfMonth=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<dayOfMonth;i++){
            transactionAmountList.add(0.0);
            orderNumList.add(0);
            profitList.add(0.0);
        }
        for (Order order:orderList) {
            totalTransactionAmount+=Double.parseDouble(String.format("%.2f",order.getConsumption()));
            totalProfit+=Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO)));
            Calendar cal=Calendar.getInstance();
            cal.setTime(order.getOrderTime());
            int day=cal.get(Calendar.DAY_OF_MONTH);
            day--;
            transactionAmountList.set(day,transactionAmountList.get(day)+Double.parseDouble(String.format("%.2f", order.getConsumption())));
            orderNumList.set(day,orderNumList.get(day)+1);
            profitList.set(day,profitList.get(day)+Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO))));
        }
        return new YummyMonthlyFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
    }

    @Override
    public YummyWeeklyFinance getYummyWeeklyFinance() {
        List<Order> orderList=orderDao.getCompletedWeeklyOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<>();
        ArrayList<Integer> orderNumList=new ArrayList<>();
        ArrayList<Double> profitList=new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek==0){
            dayOfWeek=7;
        }
        for(int i=0;i<dayOfWeek;i++){
            transactionAmountList.add(0.0);
            orderNumList.add(0);
            profitList.add(0.0);
        }
        for (Order order:orderList) {
            totalTransactionAmount+=Double.parseDouble(String.format("%.2f",order.getConsumption()));
            totalProfit+=Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO)));
            cal.setTime(order.getOrderTime());
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            int day=cal.get(Calendar.DAY_OF_WEEK)-1;
            if(day==0){
                day=7;
            }
            day--;
            transactionAmountList.set(day,transactionAmountList.get(day)+Double.parseDouble(String.format("%.2f", order.getConsumption())));
            orderNumList.set(day,orderNumList.get(day)+1);
            profitList.set(day,profitList.get(day)+Double.parseDouble(String.format("%.2f",order.getConsumption()*(1-ManagerServiceImpl.PROFIT_RATIO))));
        }
        return new YummyWeeklyFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
    }
}
