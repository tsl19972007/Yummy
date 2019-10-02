package yummy.demo.dto;

import yummy.demo.model.Menu;
import yummy.demo.model.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuDTO{

    private int id;
    private Date beginTime;
    private Date endTime;
    private List<MenuItemDTO> itemList;

    public MenuDTO(Menu menu) {
        this.id=menu.getId();
        this.beginTime = menu.getBeginTime();
        this.endTime = menu.getEndTime();
        itemList=new ArrayList<MenuItemDTO>();
        for(int i=0;i<menu.getItemList().size();i++){
            itemList.add(new MenuItemDTO(menu.getItemList().get(i)));
        }
    }


    public MenuDTO(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setItemList(List<MenuItemDTO> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public List<MenuItemDTO> getItemList() {
        return itemList;
    }

    public Menu toMenu(){
        List<MenuItem> itemList=new ArrayList<>();
        for(int i=0;i<this.itemList.size();i++){
            itemList.add(this.itemList.get(i).toMenuItem());
        }
        return new Menu(id,beginTime,endTime,itemList);
    }
}
