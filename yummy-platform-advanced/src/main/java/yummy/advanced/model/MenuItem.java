package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String type;
    private String name;
    private double price;
    private int num;

    public MenuItem(String type, String name, double price, int num) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.num = num;
    }
}
