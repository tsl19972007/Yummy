package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.model.Order;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/3 0:32
 * @description：implementation of orderService with overtime cancel by user trigger
 */

@Transactional
@Service("OrderServiceImplWithUserTriggerCancel")
public class OrderServiceImplWithUserTriggerCancel implements OrderService {
    //120s未支付订单取消
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
        List<Order> orderList = orderService.findByCst(cstId);
        return filterOrderList(orderList);//筛选出超时的待支付订单并取消
    }

    @Override
    public List<Order> findByCst(int cstId, String state) {
        List<Order> orderList = orderService.findByCst(cstId, state);
        return filterOrderList(orderList);//筛选出超时的待支付订单并取消
    }

    @Override
    public List<Order> findByRst(int rstId) {
        List<Order> orderList = orderService.findByRst(rstId);
        return filterOrderList(orderList);//筛选出超时的待支付订单并取消
    }

    @Override
    public List<Order> findByRst(int rstId, String state) {
        List<Order> orderList = orderService.findByRst(rstId, state);
        return filterOrderList(orderList);//筛选出超时的待支付订单并取消
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

    private List<Order> filterOrderList(List<Order> orderList) {
        Iterator<Order> it = orderList.iterator();
        Date now = new Date(System.currentTimeMillis() - PAY_INTERVAL);
        while (it.hasNext()) {
            Order order = it.next();
            if ("待支付".equals(order.getState()) && order.getOrderTime().before(now)) {
                cancelOrder(order.getOrderId());
                it.remove();
            }
        }
        return orderList;
    }
}
