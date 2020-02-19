package yummy.advanced.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerStatistics {
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalConsumption;
    private double todayConsumption;
    private String nextLevel;
    private double consumptionToNextLevel;
    private List<String> rstNameList;
    private List<Double> rstConsumptionList;
}
