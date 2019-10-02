package yummy.demo.dao;

import org.springframework.stereotype.Repository;
import yummy.demo.model.MenuItem;
import yummy.demo.model.Restaurant;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:56
 * @description：implementation of restaurantDao
 */

@Repository
public class RestaurantDaoImpl extends BaseDaoImpl<Restaurant> implements RestaurantDao{
    @Override
    public Restaurant findByIdAndPassword(int id, String password) {
        return getUniqueResultByHQL("SELECT r FROM Restaurant r WHERE r.id = ?0 and r.password=?1",id,password);
    }

    public MenuItem getMenuItem(int id){
        return getSession().get(MenuItem.class,id);
    }

    public void updateMenuItem(MenuItem item){
        getSession().update(item);
    }
}
