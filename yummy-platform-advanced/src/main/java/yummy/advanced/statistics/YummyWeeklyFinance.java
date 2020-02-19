package yummy.advanced.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class YummyWeeklyFinance {
    /*
    交易金额
     */
    private double totalTransactionAmount;
    /*
    订单数量
     */
    private int totalOrderNum;
    /*
    Yummy盈利
     */
    private double totalProfit;

    private List<Double> transactionAmountList;
    private List<Integer> orderNumList;
    private List<Double> profitList;
}
