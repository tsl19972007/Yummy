package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.dao.RestaurantDao;
import yummy.demo.model.Menu;
import yummy.demo.model.Restaurant;

import java.util.List;

@Transactional
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantDao rstDao;

    @Autowired
    ManagerService mngService;

    public List<Restaurant> getAllRestaurants(){
        return rstDao.getAllRestaurants();
    }

    public int register(Restaurant rst){
        return rstDao.add(rst);
    }

    public void updateRst(Restaurant rst){
        mngService.addApply(rst);
    }

    public void updateMenu(int rstId,Menu menu){
        rstDao.updateMenu(rstId,menu);
    }

    public void delete(int id){
        rstDao.delete(id);
    }

    public Restaurant login(int id,String password){
        return rstDao.login(id,password);
    }

    public Restaurant findById(int id){
        return rstDao.findById(id);
    }
}
