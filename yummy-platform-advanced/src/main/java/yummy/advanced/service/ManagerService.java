package yummy.advanced.service;

import yummy.advanced.model.Manager;
import yummy.advanced.model.Restaurant;

import java.util.List;

public interface ManagerService {
    Manager login(int id, String password);

    List<Restaurant> getRstUpdateList();

    void addApply(Restaurant rst);

    Restaurant getApplyFromId(int rstId);

    void approve(int rstId);

    void reject(int rstId);

    List<Restaurant> getRstBalanceList();

    void balance(int id);

    void balanceAll();
}
