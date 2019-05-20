package yummy.demo.statistics;

import java.util.ArrayList;

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

    private ArrayList<Double> transactionAmountList;
    private ArrayList<Integer> orderNumList;
    private ArrayList<Double> profitList;

    public YummyMonthlyFinance(double totalTransactionAmount, int totalOrderNum, double totalProfit, ArrayList<Double> transactionAmountList, ArrayList<Integer> orderNumList, ArrayList<Double> profitList) {
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

    public ArrayList<Double> getTransactionAmountList() {
        return transactionAmountList;
    }

    public void setTransactionAmountList(ArrayList<Double> transactionAmountList) {
        this.transactionAmountList = transactionAmountList;
    }

    public ArrayList<Integer> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(ArrayList<Integer> orderNumList) {
        this.orderNumList = orderNumList;
    }

    public ArrayList<Double> getProfitList() {
        return profitList;
    }

    public void setProfitList(ArrayList<Double> profitList) {
        this.profitList = profitList;
    }
}
