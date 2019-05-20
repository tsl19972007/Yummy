package yummy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int orderId;
    private int rstId;
    private int cstId;
    private String phone;
    private String address;
    private String remarks;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private List<OrderItem> itemList;
    private double consumption;
    private double discount;
    private Date orderTime;
    private Date arriveTime;
    private String state;

    public Order(int rstId, int cstId, String phone, String address, String remarks, List<OrderItem> itemList, double consumption,double discount,Date orderTime, Date arriveTime, String state) {
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

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRstId() {
        return rstId;
    }

    public void setRstId(int rstId) {
        this.rstId = rstId;
    }

    public int getCstId() {
        return cstId;
    }

    public void setCstId(int cstId) {
        this.cstId = cstId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotalPrice(){
        double total=0;
        for(OrderItem item:itemList){
            total+=item.getNum()*item.getPrice();
        }
        return total;
    }
}
