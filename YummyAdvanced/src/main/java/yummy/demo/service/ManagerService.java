package yummy.demo.service;

import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.statistics.YummyAnnualFinance;
import yummy.demo.statistics.YummyMonthlyFinance;
import yummy.demo.statistics.YummyWeeklyFinance;

import java.util.ArrayList;

public interface ManagerService {
    public void addApply(Restaurant rst);

    public Restaurant getApplyFromId(int rstId);

    public void approve(int rstId);

    public void reject(int rstId);

    public boolean login(int id,String password);

    public ArrayList<Restaurant> getRstUpdateList();

    public void balance(int id);

    public void balanceAll();

    public ArrayList<Restaurant> getRstBalanceList();

    public ArrayList<Restaurant> getAllRestaurants();

    public ArrayList<Customer> getAllCustomers();

    public YummyAnnualFinance getYummyAnnualFinance();

    public YummyMonthlyFinance getYummyMonthlyFinance();

    public YummyWeeklyFinance getYummyWeeklyFinance();
}
