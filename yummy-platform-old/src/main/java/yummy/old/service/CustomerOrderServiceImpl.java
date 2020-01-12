package yummy.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.old.dao.OrderDao;
import yummy.old.model.Order;

import java.util.List;

import static yummy.old.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public Order findById(int id) {
        Order order=orderDao.findById(id);
        order.setRstId(order.getRstId()+ID_DIFF);
        return order;
    }

    @Override
    public List<Order> findByCst(int cstId) {
        orderDao.cancelOrders();
        List<Order> orderList=orderDao.findByCst(cstId);
        for(int i=0;i<orderList.size();i++){
            orderList.get(i).setRstId(orderList.get(i).getRstId()+ID_DIFF);
        }
        return orderDao.findByCst(cstId);
    }

    @Override
    public int add(Order order) {
        order.setRstId(order.getRstId()-ID_DIFF);
        return orderDao.add(order);
    }

    @Override
    public void cancelOrder(int id) {
        orderDao.delete(id);
    }

    @Override
    public double returnOrder(int id) {
        return orderDao.returnOrder(id);
    }

    @Override
    public boolean payOrder(int id) {
        return orderDao.payOrder(id);
    }

    public void finishOrder(int id){
        orderDao.finishOrder(id);
    }
}
