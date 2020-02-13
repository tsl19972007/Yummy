package yummy.advanced.dao;

import org.springframework.stereotype.Repository;
import yummy.advanced.model.MenuItem;
import yummy.advanced.model.Restaurant;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:56
 * @description：implementation of restaurantDao
 */

@Repository
public class RestaurantDaoImpl extends BaseDaoImpl<Restaurant> implements RestaurantDao {
    public MenuItem getMenuItem(int id) {
        return getSession().get(MenuItem.class, id);
    }

    public void updateMenuItem(MenuItem item) {
        getSession().update(item);
    }
}
