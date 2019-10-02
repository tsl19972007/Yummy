package yummy.demo.service;

import yummy.demo.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:26
 * @description：service interface of order
 */

public interface OrderService {
    public Order findById(int id);

    public List<Order> findByCst(int cstId);

    public List<Order> findByCst(int cstId,String state);

    public List<Order> findByRst(int rstId);

    public List<Order> findByRst(int rstId,String state);

    public int add(Order order);

    public void cancelOrder(int id);

    public double returnOrder(int id);

    public boolean payOrder(int id);

    public void finishOrder(int id);

    public double getDiscount(int cstId,double consumption);
}
