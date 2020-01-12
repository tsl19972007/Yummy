package yummy.old.service;

import yummy.old.model.Order;

import java.util.List;

public interface RestaurantOrderService {
    public Order findById(int id);

    public List<Order> findByRst(int rstId);

    public void finishOrder(int id);
}
