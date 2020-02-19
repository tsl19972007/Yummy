package yummy.advanced.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantStatistics {
    private List<String> cstNameList;
    private List<Double> cstConsumptionList;
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalProfit;
    private double todayProfit;
    private List<Double> weekProfitList;
}
