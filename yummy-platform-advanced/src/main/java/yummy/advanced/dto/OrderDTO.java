package yummy.advanced.dto;

import yummy.advanced.model.Order;
import yummy.advanced.model.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int orderId;
    private int rstId;
    private int cstId;
    private String phone;
    private String address;
    private String remarks;
    private List<OrderItemDTO> itemList;
    private double consumption;
    private double discount;
    private Date orderTime;
    private Date arriveTime;
    private String state;


    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.rstId = order.getRstId();
        this.cstId = order.getCstId();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.remarks = order.getRemarks();
        itemList = new ArrayList<>();
        for (int i = 0; i < order.getItemList().size(); i++) {
            itemList.add(new OrderItemDTO(order.getItemList().get(i)));
        }
        this.consumption = order.getConsumption();
        this.discount = order.getDiscount();
        this.orderTime = order.getOrderTime();
        this.arriveTime = order.getArriveTime();
        this.state = order.getState();
    }

    public OrderDTO() {
    }

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

    public List<OrderItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDTO> itemList) {
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

    public double getTotalPrice() {
        double total = 0;
        for (OrderItemDTO item : itemList) {
            total += item.getNum() * item.getPrice();
        }
        return total;
    }

    public Order toOrder() {
        List<OrderItem> itemList = new ArrayList<>();
        for (int i = 0; i < this.itemList.size(); i++) {
            itemList.add(this.itemList.get(i).toOrderItem());
        }
        return new Order(orderId, rstId, cstId, phone, address, remarks, itemList, consumption, discount, orderTime, arriveTime, state);
    }
}
