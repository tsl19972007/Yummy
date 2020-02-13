package yummy.advanced.service;

import yummy.advanced.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:26
 * @description：service interface of order
 */

public interface OrderService {
    Order findById(int id);

    List<Order> findByCst(int cstId);

    List<Order> findByCst(int cstId, String state);

    List<Order> findByRst(int rstId);

    List<Order> findByRst(int rstId, String state);

    int add(Order order);

    void cancelOrder(int id);

    double returnOrder(int id);

    boolean payOrder(int id);

    void finishOrder(int id);

    double getDiscount(int cstId, double consumption);
}
