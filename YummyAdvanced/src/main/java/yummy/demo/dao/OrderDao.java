package yummy.demo.dao;

import yummy.demo.model.Order;

import java.util.List;

public interface OrderDao {
    public Order findById(int id);

    public List<Order> getAllOrders();

    public List<Order> getAnnualOrders();

    public List<Order> getMonthlyOrders();

    public List<Order> getWeeklyOrders();

    public void cancelOrders();

    public List<Order> findByCst(int cstId);

    public List<Order> findByRst(int rstId);

    public int add(Order order);

    public void update(Order order);

    public boolean payOrder(int id);

    public double returnOrder(int id);

    public void finishOrder(int id);

    public void delete(int id);
}
