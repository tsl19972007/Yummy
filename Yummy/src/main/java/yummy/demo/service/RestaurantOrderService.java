package yummy.demo.service;

import yummy.demo.model.Order;

import java.util.List;

public interface RestaurantOrderService {
    public Order findById(int id);

    public List<Order> findByRst(int rstId);

    public void finishOrder(int id);
}
