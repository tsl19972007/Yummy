package yummy.demo.service;

import yummy.demo.model.Order;
import yummy.demo.model.Restaurant;

import java.util.List;

public interface CustomerOrderService {
    public Order findById(int id);

    public List<Order> findByCst(int cstId);

    public int add(Order order);

    public void cancelOrder(int id);

    public double returnOrder(int id);

    public boolean payOrder(int id);

    public void finishOrder(int id);
}
