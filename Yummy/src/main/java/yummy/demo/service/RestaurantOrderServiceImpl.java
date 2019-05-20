package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.demo.dao.OrderDao;
import yummy.demo.model.Order;

import java.util.List;

import static yummy.demo.service.RestaurantServiceImpl.ID_DIFF;

@Service
public class RestaurantOrderServiceImpl implements RestaurantOrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public Order findById(int id) {
        Order order=orderDao.findById(id);
        order.setRstId(order.getRstId()+ID_DIFF);
        return order;
    }

    @Override
    public List<Order> findByRst(int rstId) {
        return orderDao.findByRst(rstId-RestaurantServiceImpl.ID_DIFF);
    }

    @Override
    public void finishOrder(int id) {
        orderDao.finishOrder(id);
    }
}
