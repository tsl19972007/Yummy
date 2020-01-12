package yummy.advanced.dao;

import org.springframework.stereotype.Repository;
import yummy.advanced.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 16:03
 * @description：implementation of orderDao
 */

@Repository("OrderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public List<Order> getCompletedAnnualOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE year(o.order_time) = year(now())");
    }

    @Override
    public List<Order> getCompletedMonthlyOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE month(o.order_time) = month(now())");
    }

    @Override
    public List<Order> getCompletedWeeklyOrders() {
        return getListByHQL("SELECT o FROM Order o WHERE yearweek(o.order_time) = yearweek(now())");
    }

    @Override
    public List<Order> findByCst(int cstId) {
        return getListByHQL("SELECT o FROM Order o WHERE o.cstId = ?0", cstId);
    }

    @Override
    public List<Order> findByCst(int cstId, String state) {
        return getListByHQL("SELECT o FROM Order o WHERE o.cstId = ?0 AND o.state=?1", cstId, state);
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return getListByHQL("SELECT o FROM Order o WHERE o.rstId = ?0", rstId);
    }

    @Override
    public List<Order> findByRst(int rstId, String state) {
        return getListByHQL("SELECT o FROM Order o WHERE o.rstId = ?0 AND o.state=?1", rstId, state);
    }
}
