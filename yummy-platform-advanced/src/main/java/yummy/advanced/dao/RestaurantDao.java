package yummy.advanced.dao;

import yummy.advanced.model.MenuItem;
import yummy.advanced.model.Restaurant;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:51
 * @description：dao interface of restaurant
 */

public interface RestaurantDao extends BaseDao<Restaurant> {
    MenuItem getMenuItem(int id);

    void updateMenuItem(MenuItem item);
}
