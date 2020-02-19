package yummy.advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.Customer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private Integer state;
    private List<String> addresses;
    private double consumption;
    private double balance;

    public CustomerDTO(Customer cst) {
        this.id = cst.getId();
        this.email = cst.getEmail();
        this.password = cst.getPassword();
        this.name = cst.getName();
        this.phone = cst.getPhone();
        this.state = cst.getState();
        this.addresses = cst.getAddresses();
        this.consumption = cst.getConsumption();
        this.balance = cst.getBalance();
    }

    public double getConsumptionToNextLevel() {
        if (consumption > 1000) {
            return 0;
        } else if (consumption > 100) {
            return 1000 - consumption;
        }
        return 100 - consumption;
    }

    public String getLevel() {
        if (consumption > 1000) {
            return "顶级会员";
        } else if (consumption > 100) {
            return "超级会员";
        }
        return "会员";
    }

    public String getNextLevel() {
        if (consumption > 1000) {
            return "已封顶";
        } else if (consumption > 100) {
            return "顶级会员";
        } else {
            return "超级会员";
        }
    }

    public double getLevelPercentage() {
        if (consumption > 1000) {
            return 0;
        } else if (consumption > 100) {
            return consumption / 1000;
        }
        return consumption / 100;

    }

    public double getInfoCompletePercent() {
        if (addresses.isEmpty())
            return 75.0;
        else
            return 100.0;
    }

    public Customer toCustomer() {
        return new Customer(id, email, password, name, phone, state, addresses, consumption, balance);
    }
}
