package yummy.advanced.statistics;

import java.util.List;

public class CustomerStatistics {
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalConsumption;
    private double todayConsumption;
    private String nextLevel;
    private double consumptionToNextLevel;
    private List<String> rstNameList;
    private List<Double> rstConsumptionList;

    public CustomerStatistics(int totalOrderNum, int todayOrderNum, double totalConsumption, double todayConsumption, String nextLevel, double consumptionToNextLevel, List<String> rstNameList, List<Double> rstConsumptionList) {
        this.totalOrderNum = totalOrderNum;
        this.todayOrderNum = todayOrderNum;
        this.totalConsumption = totalConsumption;
        this.todayConsumption = todayConsumption;
        this.nextLevel = nextLevel;
        this.consumptionToNextLevel = consumptionToNextLevel;
        this.rstNameList = rstNameList;
        this.rstConsumptionList = rstConsumptionList;
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

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public double getTodayConsumption() {
        return todayConsumption;
    }

    public void setTodayConsumption(double todayConsumption) {
        this.todayConsumption = todayConsumption;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public double getConsumptionToNextLevel() {
        return consumptionToNextLevel;
    }

    public void setConsumptionToNextLevel(double consumptionToNextLevel) {
        this.consumptionToNextLevel = consumptionToNextLevel;
    }

    public List<String> getRstNameList() {
        return rstNameList;
    }

    public void setRstNameList(List<String> rstNameList) {
        this.rstNameList = rstNameList;
    }

    public List<Double> getRstConsumptionList() {
        return rstConsumptionList;
    }

    public void setRstConsumptionList(List<Double> rstConsumptionList) {
        this.rstConsumptionList = rstConsumptionList;
    }
}
