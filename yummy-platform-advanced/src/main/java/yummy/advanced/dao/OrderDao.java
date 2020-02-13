package yummy.advanced.dao;

import yummy.advanced.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 16:00
 * @description：dao interface of Order
 */

public interface OrderDao extends BaseDao<Order> {
    List<Order> getCompletedAnnualOrders();

    List<Order> getCompletedMonthlyOrders();

    List<Order> getCompletedWeeklyOrders();

    List<Order> findByCst(int cstId);

    List<Order> findByCst(int cstId, String state);

    List<Order> findByRst(int rstId);

    List<Order> findByRst(int rstId, String state);
}
