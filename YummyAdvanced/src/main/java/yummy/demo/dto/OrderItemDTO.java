package yummy.demo.dto;

import yummy.demo.model.OrderItem;

public class OrderItemDTO{
    private int id;
    private int menuItemId;
    private String type;
    private String name;
    private double price;
    private int num;

    public OrderItemDTO(OrderItem item) {
        this.id = item.getId();
        this.type = item.getType();
        this.menuItemId = item.getMenuItemId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.num = item.getNum();
    }


    public OrderItemDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
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

    public double getSum(){
        return price*num;
    }

    public OrderItem toOrderItem(){
        return new OrderItem(id,menuItemId,type,name,price,num);
    }
}
