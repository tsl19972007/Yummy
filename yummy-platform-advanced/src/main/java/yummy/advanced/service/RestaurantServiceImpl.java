package yummy.advanced.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.advanced.config.shiro.ShiroUtil;
import yummy.advanced.dao.RestaurantDao;
import yummy.advanced.model.Menu;
import yummy.advanced.model.Restaurant;

import java.util.List;

@Transactional
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantDao rstDao;
    @Autowired
    ManagerService mngService;

    @Override
    public Restaurant findById(int id) {
        return rstDao.get(id);
    }

    @Override
    public int register(Restaurant rst) {
        //餐厅用手机号作盐值对密码进行md5加密
        String password = ShiroUtil.encrypt(rst.getPassword(), rst.getPhone());
        rst.setPassword(password);
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
    }

    @Override
    public List<Restaurant> getAll() {
        return rstDao.getAll();
    }
}
