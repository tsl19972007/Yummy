package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.model.Order;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/4 13:53
 * @description：implementation of orderService with overtime cancel by scheduleThreadPool
 */

@Transactional
@Service("OrderServiceImplWithThreadPoolCancel")
public class OrderServiceImplWithThreadPoolCancel implements OrderService {

    private static final long PAY_INTERVAL = 120 * 1000L;

    private static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

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
        int id = orderService.add(order);
        threadPool.schedule(() -> cancelOrder(order.getOrderId()), PAY_INTERVAL, TimeUnit.MILLISECONDS);
        return id;
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

    @PreDestroy
    public void close() {
        threadPool.shutdown();
    }
}
