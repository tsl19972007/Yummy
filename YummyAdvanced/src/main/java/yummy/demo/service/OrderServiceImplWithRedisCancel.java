package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.model.Order;

import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/4 16:37
 * @description：implementation of orderService with overtime cancel by redis(not implement yet)
 */

@Transactional
@Service("OrderServiceImplWithRedisCancel")
public class OrderServiceImplWithRedisCancel implements OrderService {

    private static final long PAY_INTERVAL = 120 * 1000L;

    @Autowired
    @Qualifier("OrderServiceImplWithoutCancel")
    OrderService orderService;

    @Override
    public Order findById(int id) {
        return orderService.findById(id);
    }

    @Override
    public List<Order> findByCst(int cstId) {
        return orderService.findByCst(cstId);
    }

    @Override
    public List<Order> findByCst(int cstId, String state) {
        return orderService.findByCst(cstId, state);
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return orderService.findByRst(rstId);
    }

    @Override
    public List<Order> findByRst(int rstId, String state) {
        return orderService.findByRst(rstId, state);
    }

    @Override
    public int add(Order order) {
        return orderService.add(order);
    }

    @Override
    public void cancelOrder(int id) {
        orderService.cancelOrder(id);
    }

    @Override
    public double returnOrder(int id) {
        return orderService.returnOrder(id);
    }

    @Override
    public boolean payOrder(int id) {
        return orderService.payOrder(id);
    }

    @Override
    public void finishOrder(int id) {
        orderService.finishOrder(id);
    }

    @Override
    public double getDiscount(int cstId, double consumption) {
        return orderService.getDiscount(cstId, consumption);
    }
}
