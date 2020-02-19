package yummy.advanced.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.Restaurant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private int id;
    private String password;
    private String type;
    private String name;
    private String phone;
    private String address;
    private MenuDTO menuDTO;
    private double profit;
    private double balance;

    public RestaurantDTO(Restaurant rst) {
        this.id = rst.getId();
        this.password = rst.getPassword();
        this.type = rst.getType();
        this.name = rst.getName();
        this.phone = rst.getPhone();
        this.address = rst.getAddress();
        if (rst.getMenu() != null) this.menuDTO = new MenuDTO(rst.getMenu());
        this.profit = rst.getProfit();
        this.balance = rst.getProfit();
    }

    public Restaurant toRestaurant() {
        if (menuDTO == null) return new Restaurant(id, password, type, name, phone, address, null, profit, balance);
        return new Restaurant(id, password, type, name, phone, address, menuDTO.toMenu(), profit, balance);
    }
}
