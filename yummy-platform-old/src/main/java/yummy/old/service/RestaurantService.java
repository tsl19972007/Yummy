package yummy.old.service;

import yummy.old.model.Menu;
import yummy.old.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getAllRestaurants();

    public int register(Restaurant rst);

    public void updateRst(Restaurant rst);

    public void updateMenu(int rstId,Menu menu);

    public void delete(int id);

    public Restaurant login(int id,String password);

    public Restaurant findById(int id);
}
