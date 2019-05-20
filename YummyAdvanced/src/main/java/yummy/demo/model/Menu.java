package yummy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private Date beginTime;
    private Date endTime;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id")
    private List<MenuItem> itemList;

    public Menu(int id, Date beginTime, Date endTime, List<MenuItem> itemList) {
        this.id=id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.itemList = itemList;
    }

    public Menu(Date beginTime, Date endTime, List<MenuItem> itemList) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.itemList = itemList;
    }

    public Menu(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<MenuItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MenuItem> itemList) {
        this.itemList = itemList;
    }
}
