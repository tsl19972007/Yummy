package yummy.advanced.service;

import yummy.advanced.model.Menu;
import yummy.advanced.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Restaurant login(int id, String password);

    public Restaurant findById(int id);

    public int register(Restaurant rst);

    public void updateRst(Restaurant rst);

    public void updateMenu(int rstId, Menu menu);

    public List<Restaurant> getAll();
}