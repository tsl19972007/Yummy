package yummy.demo.service;

import yummy.demo.model.Restaurant;

import java.util.List;

public interface ManagerService {
    public boolean login(int id,String password);

    public List<Restaurant> getRstUpdateList();

    public void addApply(Restaurant rst);

    public Restaurant getApplyFromId(int rstId);

    public void approve(int rstId);

    public void reject(int rstId);

    public List<Restaurant> getRstBalanceList();

    public void balance(int id);

    public void balanceAll();
}
