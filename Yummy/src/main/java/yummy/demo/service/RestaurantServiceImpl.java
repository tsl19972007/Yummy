package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Menu;
import yummy.demo.model.Restaurant;

import java.util.List;


@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantDao rstDao;

    @Autowired
    ManagerService mngService;

    public static int ID_DIFF=1000000;

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> rstList=rstDao.getAllRestaurants();
        for(int i=0;i<rstList.size();i++){
            rstList.get(i).setId(rstList.get(i).getId()+ID_DIFF);
        }
        return rstList;
    }

    public int register(Restaurant rst){
        return ID_DIFF+rstDao.add(rst);
    }

    public void updateRst(Restaurant rst){
        mngService.addApply(rst);
    }

    public void updateMenu(int rstId,Menu menu){
        rstDao.updateMenu(rstId-ID_DIFF,menu);
    }

    public void delete(int id){
        rstDao.delete(id-ID_DIFF);
    }

    public Restaurant login(int id,String password){
        return rstDao.login(id-ID_DIFF,password);
    }

    public Restaurant findById(int id){
        Restaurant rst=rstDao.findById(id-ID_DIFF);
        rst.setId(rst.getId()+ID_DIFF);
        return rst;
    }
}
