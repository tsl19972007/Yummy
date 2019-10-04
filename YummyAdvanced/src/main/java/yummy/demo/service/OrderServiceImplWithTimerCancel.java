package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.demo.model.Order;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/3 0:59
 * @description：implementation of orderService with overtime cancel by timer
 */


@Transactional
@Service("OrderServiceImplWithTimerCancel")
public class OrderServiceImplWithTimerCancel implements OrderService{
    //120s未支付订单取消
    private static final long PAY_INTERVAL =  120 * 1000L;

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
        return orderService.findByCst(cstId,state);
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return orderService.findByRst(rstId);
    }

    @Override
    public List<Order> findByRst(int rstId, String state) {
        return orderService.findByRst(rstId,state);
    }

    @Override
    public int add(Order order) {
        int id=orderService.add(order);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 处理延时订单
                cancelOrderIfOvertime(order.getOrderId());
            }
        }, (2 * 60 * 1000));
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
        return orderService.getDiscount(cstId,consumption);
    }

    private void cancelOrderIfOvertime(int orderId){
        Order order=findById(orderId);
        if(order==null) return;//订单已经被取消
        Date now=new Date(System.currentTimeMillis()-PAY_INTERVAL);
        if("待支付".equals(order.getState())&&order.getOrderTime().before(now)){
            cancelOrder(orderId);
        }
    }
}
