package yummy.advanced.service;

import yummy.advanced.statistics.*;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 12:30
 * @description：service interface of Statistics
 */

public interface StatisticsService {
    CustomerStatistics getCustomerStatistics(int cstId);

    RestaurantStatistics getRestaurantStatistics(int rstId);

    YummyAnnualFinance getYummyAnnualFinance();

    YummyMonthlyFinance getYummyMonthlyFinance();

    YummyWeeklyFinance getYummyWeeklyFinance();
}
