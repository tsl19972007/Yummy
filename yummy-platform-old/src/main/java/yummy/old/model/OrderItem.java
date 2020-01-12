package yummy.old.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int menuItemId;
    private String name;
    private double price;
    private int num;

    public OrderItem(int id, int menuItemId, String name, double price, int num) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public OrderItem(int menuItemId, String name, double price, int num) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public OrderItem(){};

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
}
