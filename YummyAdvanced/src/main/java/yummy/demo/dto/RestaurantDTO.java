package yummy.demo.dto;


import yummy.demo.model.Restaurant;

import java.io.Serializable;

public class RestaurantDTO implements Serializable{
    private int id;
    private String password;
    private String type;
    private String name;
    private String phone;
    private String address;
    private MenuDTO menuDTO;
    private double profit;
    private double balance;

    public RestaurantDTO(Restaurant rst) {
        this.id = rst.getId();
        this.password = rst.getPassword();
        this.type = rst.getType();
        this.name = rst.getName();
        this.phone = rst.getPhone();
        this.address = rst.getAddress();
        if(rst.getMenu()!=null) this.menuDTO = new MenuDTO(rst.getMenu());
        this.profit = rst.getProfit();
        this.balance = rst.getProfit();
    }


    public RestaurantDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Restaurant toRestaurant(){
        if(menuDTO==null) return new Restaurant(id,password,type,name,phone,address,null,profit,balance);
        return new Restaurant(id,password,type,name,phone,address,menuDTO.toMenu(),profit,balance);
    }
}
