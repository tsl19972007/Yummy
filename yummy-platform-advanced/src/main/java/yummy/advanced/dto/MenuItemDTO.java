package yummy.advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.MenuItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {
    private int id;
    private String type;
    private String name;
    private double price;
    private int num;

    public MenuItemDTO(MenuItem item) {
        this.id = item.getId();
        this.type = item.getType();
        this.name = item.getName();
        this.price = item.getPrice();
        this.num = item.getNum();
    }

    public MenuItem toMenuItem() {
        return new MenuItem(id, type, name, price, num);
    }
}
