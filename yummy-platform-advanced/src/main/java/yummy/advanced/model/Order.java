package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`", indexes = {@Index(name = "cstId_index", columnList = "cstId"),
        @Index(name = "rstId_index", columnList = "rstId"), @Index(name = "orderTime_index", columnList = "orderTime")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int orderId;
    private int rstId;
    private int cstId;
    private String phone;
    private String address;
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> itemList;
    private double consumption;
    private double discount;
    private Date orderTime;
    private Date arriveTime;
    private String state;

    public Order(int rstId, int cstId, String phone, String address, String remarks, List<OrderItem> itemList, double consumption, double discount, Date orderTime, Date arriveTime, String state) {
        this.rstId = rstId;
        this.cstId = cstId;
        this.phone = phone;
        this.address = address;
        this.remarks = remarks;
        this.itemList = itemList;
        this.consumption = consumption;
        this.discount = discount;
        this.orderTime = orderTime;
        this.arriveTime = arriveTime;
        this.state = state;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : itemList) {
            total += item.getNum() * item.getPrice();
        }
        return total;
    }
}
