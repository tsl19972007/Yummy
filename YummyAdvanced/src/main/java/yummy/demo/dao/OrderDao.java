package yummy.demo.dao;

import yummy.demo.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 16:00
 * @description：dao interface of Order
 */

public interface OrderDao extends BaseDao<Order> {
    public List<Order> getCompletedAnnualOrders();

    public List<Order> getCompletedMonthlyOrders();

    public List<Order> getCompletedWeeklyOrders();

    public List<Order> findByCst(int cstId);

    public List<Order> findByCst(int cstId, String state);

    public List<Order> findByRst(int rstId);

    public List<Order> findByRst(int rstId, String state);
}
