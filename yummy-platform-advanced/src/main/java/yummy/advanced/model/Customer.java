package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private Integer state;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "address")
    private List<String> addresses;
    private double consumption;
    private double balance;

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

    public double getConsumptionToNextLevel() {
        if (consumption > 1000) {
            return 0;
        } else if (consumption > 100) {
            return 1000 - consumption;
        }
        return 100 - consumption;
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
}
