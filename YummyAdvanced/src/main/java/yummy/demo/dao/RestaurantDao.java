package yummy.demo.dao;

import yummy.demo.model.MenuItem;
import yummy.demo.model.Restaurant;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:51
 * @description：dao interface of restaurant
 */

public interface RestaurantDao extends BaseDao<Restaurant> {
    public Restaurant findByIdAndPassword(int id, String password);

    public MenuItem getMenuItem(int id);

    public void updateMenuItem(MenuItem item);
}
