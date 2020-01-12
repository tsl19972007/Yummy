package yummy.advanced.dto;

import yummy.advanced.model.MenuItem;

public class MenuItemDTO {
    private int id;
    private String type;
    private String name;
    private double price;
    private int num;

    public MenuItemDTO(MenuItem item) {
        this.id = item.getId();
        this.type = item.getType();
        this.name = item.getName();
        this.price = item.getPrice();
        this.num = item.getNum();
    }

    public MenuItemDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public MenuItem toMenuItem() {
        return new MenuItem(id, type, name, price, num);
    }
}
