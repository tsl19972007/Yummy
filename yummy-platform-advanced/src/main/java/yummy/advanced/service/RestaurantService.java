package yummy.advanced.service;

import yummy.advanced.model.Menu;
import yummy.advanced.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant findById(int id);

    int register(Restaurant rst);

    void updateRst(Restaurant rst);

    void updateMenu(int rstId, Menu menu);

    List<Restaurant> getAll();
}
