package yummy.demo.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String password;
    private String type;
    private String name;
    private String phone;
    private String address;

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id")
    private Menu menu;
    private double profit;
    private double balance;

    public Restaurant(String password, String type, String name, String phone, String address) {
        this.password = password;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.balance = 0;
        this.profit = 0;
    }

    public Restaurant(int id, String password, String type, String name, String phone, String address, Menu menu, double balance) {
        this.id = id;
        this.password = password;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.menu = menu;
        this.balance = balance;
        this.profit = profit;
    }

    public Restaurant(){};

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
}
