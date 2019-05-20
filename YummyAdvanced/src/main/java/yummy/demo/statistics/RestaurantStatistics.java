package yummy.demo.statistics;

import java.util.ArrayList;

public class RestaurantStatistics {
    private ArrayList<String> cstNameList;
    private ArrayList<Double> cstConsumptionList;
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalProfit;
    private double todayProfit;
    private ArrayList<Double> weekProfitList;

    public ArrayList<String> getCstNameList() {
        return cstNameList;
    }

    public void setCstNameList(ArrayList<String> cstNameList) {
        this.cstNameList = cstNameList;
    }

    public ArrayList<Double> getCstConsumptionList() {
        return cstConsumptionList;
    }

    public void setCstConsumptionList(ArrayList<Double> cstConsumptionList) {
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

    public ArrayList<Double> getWeekProfitList() {
        return weekProfitList;
    }

    public void setWeekProfitList(ArrayList<Double> weekProfitList) {
        this.weekProfitList = weekProfitList;
    }

    public RestaurantStatistics(ArrayList<String> cstNameList, ArrayList<Double> cstConsumptionList, int totalOrderNum, int todayOrderNum, double totalProfit, double todayProfit, ArrayList<Double> weekProfitList) {
        this.cstNameList = cstNameList;
        this.cstConsumptionList = cstConsumptionList;
        this.totalOrderNum = totalOrderNum;
        this.todayOrderNum = todayOrderNum;
        this.totalProfit = totalProfit;
        this.todayProfit = todayProfit;
        this.weekProfitList = weekProfitList;
    }
}
