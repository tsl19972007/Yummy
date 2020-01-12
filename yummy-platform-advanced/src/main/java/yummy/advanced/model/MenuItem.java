package yummy.advanced.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu_item")
public class MenuItem implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String type;
    private String name;
    private double price;
    private int num;

    public MenuItem(int id, String type, String name, double price, int num) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public MenuItem(String type, String name, double price, int num) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public MenuItem() {}

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
}
