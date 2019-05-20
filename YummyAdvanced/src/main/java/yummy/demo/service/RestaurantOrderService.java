package yummy.demo.service;

import yummy.demo.model.Order;
import yummy.demo.statistics.CustomerStatistics;
import yummy.demo.statistics.RestaurantStatistics;

import java.util.List;

public interface RestaurantOrderService {
    public Order findById(int id);

    public List<Order> findByRst(int rstId);

    public List<Order> findPaidByRst(int rstId);

    public List<Order> findCompletedByRst(int rstId);

    public List<Order> findReturnedByRst(int rstId);

    public void finishOrder(int id);

    public RestaurantStatistics getRestaurantStatistics(int rstId);
}
