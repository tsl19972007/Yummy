package yummy.demo.service;

import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.statistics.YummyAnnualFinance;
import yummy.demo.statistics.YummyMonthlyFinance;
import yummy.demo.statistics.YummyWeeklyFinance;

import java.util.List;

public interface ManagerService {
    public void addApply(Restaurant rst);

    public Restaurant getApplyFromId(int rstId);

    public void approve(int rstId);

    public void reject(int rstId);

    public boolean login(int id,String password);

    public List<Restaurant> getRstUpdateList();

    public void balance(int id);

    public void balanceAll();

    public List<Restaurant> getRstBalanceList();

    public List<Restaurant> getAllRestaurants();

    public List<Customer> getAllCustomers();

    public YummyAnnualFinance getYummyAnnualFinance();

    public YummyMonthlyFinance getYummyMonthlyFinance();

    public YummyWeeklyFinance getYummyWeeklyFinance();
}
