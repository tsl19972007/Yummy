package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager implements Serializable{
    private static final int DEFAULT_ID = 161250128;

    @Id
    private int id;
    private String password;
    private double balance;

    public static int getDefaultId() {
        return DEFAULT_ID;
    }
}
