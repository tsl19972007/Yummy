package yummy.advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.Order;
import yummy.advanced.model.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
