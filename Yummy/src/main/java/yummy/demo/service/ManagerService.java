package yummy.demo.service;

import yummy.demo.model.Restaurant;

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
}
