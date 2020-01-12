package yummy.advanced.service;

import yummy.advanced.statistics.*;
import yummy.advanced.statistics.*;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:30
 * @description：service interface of Statistics
 */

public interface StatisticsService {
    public CustomerStatistics getCustomerStatistics(int cstId);

    public RestaurantStatistics getRestaurantStatistics(int rstId);

    public YummyAnnualFinance getYummyAnnualFinance();

    public YummyMonthlyFinance getYummyMonthlyFinance();

    public YummyWeeklyFinance getYummyWeeklyFinance();
}
