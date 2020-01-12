package yummy.advanced.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "manager")
public class Manager implements Serializable{
    private static final int DEFAULT_ID = 161250128;
    private static final String DEFAULT_PASSWORD = "yummy";

    @Id
    private int id;
    private String password;
    private double balance;

    public Manager(int id, String password, double balance) {
        this.id = id;
        this.password = password;
        this.balance = balance;
    }

    public Manager(){}

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static int getDefaultId() {
        return DEFAULT_ID;
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }
}
