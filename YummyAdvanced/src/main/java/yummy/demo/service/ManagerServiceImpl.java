package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.CustomerDao;
import yummy.demo.dao.OrderDao;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Customer;
import yummy.demo.model.Manager;
import yummy.demo.model.Order;
import yummy.demo.model.Restaurant;
import yummy.demo.statistics.YummyAnnualFinance;
import yummy.demo.statistics.YummyMonthlyFinance;
import yummy.demo.statistics.YummyWeeklyFinance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static yummy.demo.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    RestaurantDao rstDao;
    @Autowired
    CustomerDao cstDao;
    @Autowired
    OrderDao orderDao;

    public static final Double PROFIT_RATIO=0.95;

    private ArrayList<Restaurant> rstList=new ArrayList<Restaurant>();

    @Override
    public ArrayList<Restaurant> getRstUpdateList() {
        return rstList;
    }

    @Override
    public Restaurant getApplyFromId(int rstId){
        Restaurant rst=null;
        for(int i=0;i<rstList.size();i++) {
            if (rstId == rstList.get(i).getId()) {
                rst = rstList.get(i);
            }
        }
        return rst;
    }

    @Override
    public void addApply(Restaurant rst) {
        for(int i=0;i<rstList.size();i++){
            if(rst.getId()==rstList.get(i).getId()){
                rstList.remove(i);
            }
        }
        rstList.add(rst);
    }

    @Override
    public void approve(int rstId) {
        for(int i=0;i<rstList.size();i++){
            if(rstId==rstList.get(i).getId()){
                Restaurant rst=rstList.get(i);
                rstList.remove(i);
                rst.setId(rst.getId()-ID_DIFF);
                rstDao.updateRst(rst);
            }
        }
    }

    @Override
    public void reject(int rstId) {
        for(int i=0;i<rstList.size();i++){
            if(rstId==rstList.get(i).getId()){
                rstList.remove(i);
            }
        }
    }

    @Override
    public boolean login(int id, String password) {
        return (id== Manager.getDefaultId()&&password.equals(Manager.getDefaultPassword()));
    }

    @Override
    public ArrayList<Restaurant> getRstBalanceList() {
        ArrayList<Restaurant> rstList=new ArrayList<Restaurant>();
        List list=rstDao.getAllRestaurants();
        for(Object obj:list){
            Restaurant rst=(Restaurant)obj;
            if(rst.getProfit()>0) {
                rst.setId(rst.getId() + ID_DIFF);
                rstList.add(rst);
            }
        }
        return rstList;
    }



    public void balance(int rstId){
        rstDao.balance(rstId-ID_DIFF);
    }

    public void balanceAll(){
        rstDao.balanceAll();
    }

    @Override
    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> rstList=new ArrayList<Restaurant>();
        List list=rstDao.getAllRestaurants();
        for(Object obj:list){
            rstList.add((Restaurant)obj);
        }
        return rstList;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> cstList=new ArrayList<Customer>();
        List list=cstDao.getAllCustomers();
        for(Object obj:list){
            cstList.add((Customer)obj);
        }
        return cstList;
    }

    @Override
    public YummyAnnualFinance getYummyAnnualFinance() {
        List<Order> orderList=orderDao.getAnnualOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<Double>();
        ArrayList<Integer> orderNumList=new ArrayList<Integer>();
        ArrayList<Double> profitList=new ArrayList<Double>();
        int monthOfYear=Calendar.getInstance().get(Calendar.MONTH)+1;;
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
        YummyAnnualFinance annualFinance=new YummyAnnualFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
        return annualFinance;
    }

    @Override
    public YummyMonthlyFinance getYummyMonthlyFinance() {
        List<Order> orderList=orderDao.getMonthlyOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<Double>();
        ArrayList<Integer> orderNumList=new ArrayList<Integer>();
        ArrayList<Double> profitList=new ArrayList<Double>();
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
        YummyMonthlyFinance monthlyFinance=new YummyMonthlyFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
        return monthlyFinance;
    }

    @Override
    public YummyWeeklyFinance getYummyWeeklyFinance() {
        List<Order> orderList=orderDao.getWeeklyOrders();
        double totalTransactionAmount=0;
        int totalOrderNum=orderList.size();
        double totalProfit=0;
        ArrayList<Double> transactionAmountList=new ArrayList<Double>();
        ArrayList<Integer> orderNumList=new ArrayList<Integer>();
        ArrayList<Double> profitList=new ArrayList<Double>();
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
        YummyWeeklyFinance weeklyFinance=new YummyWeeklyFinance(totalTransactionAmount,totalOrderNum,totalProfit,transactionAmountList,orderNumList,profitList);
        return weeklyFinance;
    }
}
