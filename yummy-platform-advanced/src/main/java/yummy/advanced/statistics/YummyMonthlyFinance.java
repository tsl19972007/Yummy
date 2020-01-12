package yummy.advanced.statistics;

import java.util.List;

public class YummyMonthlyFinance {
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

    public YummyMonthlyFinance(double totalTransactionAmount, int totalOrderNum, double totalProfit, List<Double> transactionAmountList, List<Integer> orderNumList, List<Double> profitList) {
        this.totalTransactionAmount = totalTransactionAmount;
        this.totalOrderNum = totalOrderNum;
        this.totalProfit = totalProfit;
        this.transactionAmountList = transactionAmountList;
        this.orderNumList = orderNumList;
        this.profitList = profitList;
    }

    public double getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(double totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public int getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(int totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public List<Double> getTransactionAmountList() {
        return transactionAmountList;
    }

    public void setTransactionAmountList(List<Double> transactionAmountList) {
        this.transactionAmountList = transactionAmountList;
    }

    public List<Integer> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(List<Integer> orderNumList) {
        this.orderNumList = orderNumList;
    }

    public List<Double> getProfitList() {
        return profitList;
    }

    public void setProfitList(List<Double> profitList) {
        this.profitList = profitList;
    }
}
