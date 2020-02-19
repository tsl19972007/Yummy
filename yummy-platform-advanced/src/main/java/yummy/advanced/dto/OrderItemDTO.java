package yummy.advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.OrderItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private int id;
    private int menuItemId;
    private String type;
    private String name;
    private double price;
    private int num;

    public OrderItemDTO(OrderItem item) {
        this.id = item.getId();
        this.type = item.getType();
        this.menuItemId = item.getMenuItemId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.num = item.getNum();
    }

    public OrderItem toOrderItem() {
        return new OrderItem(id, menuItemId, type, name, price, num);
    }
}
