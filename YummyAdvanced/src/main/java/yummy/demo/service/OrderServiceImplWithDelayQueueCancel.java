package yummy.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.model.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/4 1:11
 * @description：implementation of orderService with overtime cancel by delayQueue
 */

@Transactional
@Service("OrderServiceImplWithDelayQueueCancel")
public class OrderServiceImplWithDelayQueueCancel implements OrderService {

    private static final long PAY_INTERVAL = 120 * 1000L;
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplWithDelayQueueCancel.class);
    private static DelayQueue<OrderMessage> queue = new DelayQueue<>();
    @Autowired
    @Qualifier("OrderServiceImplWithoutCancel")
    OrderService orderService;
    private Thread cancelOrderThread;

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
        queue.put(new OrderMessage(id, PAY_INTERVAL));//订单信息放入延时队列
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

    //@PostConstruct：当整个bean被初始化完成后执行
    @PostConstruct
    public void init() {
        cancelOrderThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                OrderMessage om = null;
                try {
                    om = queue.take();
                } catch (InterruptedException e) {
                    logger.error("", e);
                    Thread.currentThread().interrupt();
                }
                if (om == null) return;
                cancelOrder(om.getOrderId());
            }
        });
        cancelOrderThread.start();
    }

    @PreDestroy
    public void close() {
        cancelOrderThread.interrupt();
    }
}
