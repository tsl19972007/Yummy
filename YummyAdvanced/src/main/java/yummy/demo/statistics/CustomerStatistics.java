package yummy.demo.statistics;

import java.util.ArrayList;

public class CustomerStatistics {
    private int totalOrderNum;
    private int todayOrderNum;
    private double totalConsumption;
    private double todayConsumption;
    private String nextLevel;
    private double consumptionToNextLevel;
    private ArrayList<String> rstNameList;
    private ArrayList<Double> rstConsumptionList;

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

    public ArrayList<String> getRstNameList() {
        return rstNameList;
    }

    public void setRstNameList(ArrayList<String> rstNameList) {
        this.rstNameList = rstNameList;
    }

    public ArrayList<Double> getRstConsumptionList() {
        return rstConsumptionList;
    }

    public void setRstConsumptionList(ArrayList<Double> rstConsumptionList) {
        this.rstConsumptionList = rstConsumptionList;
    }

    public CustomerStatistics(int totalOrderNum, int todayOrderNum, double totalConsumption, double todayConsumption, String nextLevel, double consumptionToNextLevel, ArrayList<String> rstNameList, ArrayList<Double> rstConsumptionList) {
        this.totalOrderNum = totalOrderNum;
        this.todayOrderNum = todayOrderNum;
        this.totalConsumption = totalConsumption;
        this.todayConsumption = todayConsumption;
        this.nextLevel = nextLevel;
        this.consumptionToNextLevel = consumptionToNextLevel;
        this.rstNameList = rstNameList;
        this.rstConsumptionList = rstConsumptionList;
    }
}
