package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int menuItemId;
    private String type;
    private String name;
    private double price;
    private int num;

    public OrderItem(int menuItemId, String type, String name, double price, int num) {
        this.menuItemId = menuItemId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.num = num;
    }
}
