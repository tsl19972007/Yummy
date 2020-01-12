package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.dao.ManagerDao;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Manager;
import yummy.demo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ManagerServiceImpl implements ManagerService {

    public static final Double PROFIT_RATIO = 0.95;

    @Autowired
    RestaurantDao rstDao;
    @Autowired
    ManagerDao mngDao;
    private List<Restaurant> rstList = new ArrayList<>();

    @Override
    public boolean login(int id, String password) {
        return id == Manager.getDefaultId() && Manager.getDefaultPassword().equals(password);
    }

    @Override
    public Restaurant getApplyFromId(int rstId) {
        Restaurant rst = null;
        for (int i = 0; i < rstList.size(); i++) {
            if (rstId == rstList.get(i).getId()) {
                rst = rstList.get(i);
            }
        }
        return rst;
    }

    @Override
    public void addApply(Restaurant rst) {
        for (int i = 0; i < rstList.size(); i++) {
            if (rst.getId() == rstList.get(i).getId()) {
                rstList.remove(i);
            }
        }
        rstList.add(rst);
    }

    @Override
    public void approve(int rstId) {
        for (int i = 0; i < rstList.size(); i++) {
            if (rstId == rstList.get(i).getId()) {
                Restaurant rst = rstList.get(i);
                rstList.remove(i);
                rst.setId(rst.getId());
                rstDao.update(rst);
            }
        }
    }

    @Override
    public void reject(int rstId) {
        for (int i = 0; i < rstList.size(); i++) {
            if (rstId == rstList.get(i).getId()) {
                rstList.remove(i);
            }
        }
    }


    @Override
    public List<Restaurant> getRstBalanceList() {
        List<Restaurant> rstList2 = new ArrayList<>();
        List list = rstDao.getAll();
        for (Object obj : list) {
            Restaurant rst = (Restaurant) obj;
            if (rst.getProfit() > 0) {
                rstList2.add(rst);
            }
        }
        return rstList2;
    }

    @Override
    public List<Restaurant> getRstUpdateList() {
        return rstList;
    }

    @Override
    public void balance(int rstId) {
        Restaurant rst = rstDao.get(rstId);
        double profit = rst.getProfit();
        rst.setProfit(0);
        rst.setBalance(rst.getBalance() + profit * ManagerServiceImpl.PROFIT_RATIO);
        rstDao.update(rst);
        Manager mng = mngDao.get(Manager.getDefaultId());
        mng.setBalance(mng.getBalance() - profit * ManagerServiceImpl.PROFIT_RATIO);
        mngDao.update(mng);
    }

    @Override
    public void balanceAll() {
        List<Restaurant> rstList = getRstBalanceList();
        double totalProfit = 0;
        for (Restaurant rst : rstList) {
            totalProfit += rst.getProfit();
            rst.setBalance(rst.getBalance() + PROFIT_RATIO * rst.getProfit());
            rst.setProfit(0);
        }
        Manager mng = mngDao.get(Manager.getDefaultId());
        mng.setBalance(mng.getBalance() - totalProfit * ManagerServiceImpl.PROFIT_RATIO);
        mngDao.update(mng);
    }
}


