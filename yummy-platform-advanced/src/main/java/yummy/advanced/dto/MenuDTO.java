package yummy.advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yummy.advanced.model.Menu;
import yummy.advanced.model.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private int id;
    private Date beginTime;
    private Date endTime;
    private List<MenuItemDTO> itemList;

    public MenuDTO(Menu menu) {
        this.id = menu.getId();
        this.beginTime = menu.getBeginTime();
        this.endTime = menu.getEndTime();
        itemList = new ArrayList<MenuItemDTO>();
        for (int i = 0; i < menu.getItemList().size(); i++) {
            itemList.add(new MenuItemDTO(menu.getItemList().get(i)));
        }
    }

    public Menu toMenu() {
        List<MenuItem> itemList = new ArrayList<>();
        for (int i = 0; i < this.itemList.size(); i++) {
            itemList.add(this.itemList.get(i).toMenuItem());
        }
        return new Menu(id, beginTime, endTime, itemList);
    }
}
