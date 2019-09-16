package yummy.demo.service;

import yummy.demo.model.Order;
import yummy.demo.statistics.CustomerStatistics;

import java.util.List;

public interface CustomerOrderService {
    public Order findById(int id);

    public List<Order> findByCst(int cstId);

    public List<Order> findUnpaidByCst(int cstId);

    public List<Order> findPaidByCst(int cstId);

    public List<Order> findCompletedByCst(int cstId);

    public List<Order> findReturnedByCst(int cstId);

    public int add(Order order);

    public void cancelOrder(int id);

    public double returnOrder(int id);

    public boolean payOrder(int id);

    public void finishOrder(int id);

    public double getDiscount(int cstId,double consumption);

    public CustomerStatistics getCustomerStatistics(int cstId);
}
