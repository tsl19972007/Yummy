package yummy.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private Date beginTime;
    private Date endTime;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id")
    private List<MenuItem> itemList;

    public Menu(Date beginTime, Date endTime, List<MenuItem> itemList) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.itemList = itemList;
    }
}
