package yummy.advanced.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String password;
    private String type;
    private String name;
    private String phone;
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
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
}
