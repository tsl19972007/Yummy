package yummy.demo.statistics;

import java.util.List;

public class RestaurantStatistics {
    private List<String> cstNameList;
    private List<Double> cstConsumptionList;
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalProfit;
    private double todayProfit;
    private List<Double> weekProfitList;

    public List<String> getCstNameList() {
        return cstNameList;
    }

    public void setCstNameList(List<String> cstNameList) {
        this.cstNameList = cstNameList;
    }

    public List<Double> getCstConsumptionList() {
        return cstConsumptionList;
    }

    public void setCstConsumptionList(List<Double> cstConsumptionList) {
        this.cstConsumptionList = cstConsumptionList;
    }

    public int getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(int totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public int getTodayOrderNum() {
        return todayOrderNum;
    }

    public void setTodayOrderNum(int todayOrderNum) {
        this.todayOrderNum = todayOrderNum;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(double todayProfit) {
        this.todayProfit = todayProfit;
    }

    public List<Double> getWeekProfitList() {
        return weekProfitList;
    }

    public void setWeekProfitList(List<Double> weekProfitList) {
        this.weekProfitList = weekProfitList;
    }

    public RestaurantStatistics(List<String> cstNameList, List<Double> cstConsumptionList, int totalOrderNum, int todayOrderNum, double totalProfit, double todayProfit, List<Double> weekProfitList) {
        this.cstNameList = cstNameList;
        this.cstConsumptionList = cstConsumptionList;
        this.totalOrderNum = totalOrderNum;
        this.todayOrderNum = todayOrderNum;
        this.totalProfit = totalProfit;
        this.todayProfit = todayProfit;
        this.weekProfitList = weekProfitList;
    }
}
