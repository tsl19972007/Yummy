package yummy.old.service;

import yummy.old.model.Order;

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
