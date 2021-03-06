package yummy.old.dao;

import yummy.old.model.Menu;
import yummy.old.model.Restaurant;

import java.util.List;


public interface RestaurantDao {
    public List<Restaurant> getAllRestaurants();

    public Restaurant findById(int id);

    public int add(Restaurant rst);

    public void updateRst(Restaurant rst);

    public void updateMenu(int rstId,Menu menu);

    public void delete(int id);

    public Restaurant login(int id,String password);

    public void balance(int id);

    public void balanceAll();
}
