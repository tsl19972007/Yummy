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

    @Override
    public Restaurant login(int id, String password) {
        return rstDao.findByIdAndPassword(id, password);
    }

    @Override
    public Restaurant findById(int id) {
        return rstDao.get(id);
    }

    @Override
    public int register(Restaurant rst) {
        return (int) rstDao.add(rst);
    }

    @Override
    public void updateRst(Restaurant rst) {
        mngService.addApply(rst);
    }

    @Override
    public void updateMenu(int rstId, Menu menu) {
        Restaurant rst = rstDao.get(rstId);
        rst.setMenu(menu);
        rstDao.update(rst);
    }

    @Override
    public List<Restaurant> getAll() {
        return rstDao.getAll();
    }
}
