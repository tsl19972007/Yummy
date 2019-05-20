package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Manager;
import yummy.demo.model.Restaurant;
import java.util.ArrayList;
import java.util.List;

import static yummy.demo.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    RestaurantDao rstDao;

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
}
