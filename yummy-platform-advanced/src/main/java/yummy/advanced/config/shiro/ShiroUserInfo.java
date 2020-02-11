package yummy.advanced.config.shiro;

import yummy.advanced.model.Customer;
import yummy.advanced.model.Manager;
import yummy.advanced.model.Restaurant;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/11 15:22
 * @description：userInfo saved in shiro session
 */

public class ShiroUserInfo {
    //数据库主键Id:cstId,rstId或mngId
    private Integer userId;
    //右上角显示的用户名
    private String userName;
    //用户角色
    private String role;

    public ShiroUserInfo(Customer cst) {
        this.userId = cst.getId();
        this.userName = cst.getName();
        this.role = RoleEnum.Customer.getRole();
    }

    public ShiroUserInfo(Restaurant rst) {
        this.userId = rst.getId();
        this.userName = rst.getName();
        this.role = RoleEnum.Restaurant.getRole();
    }

    public ShiroUserInfo(Manager mng) {
        this.userId = mng.getId();
        this.userName = RoleEnum.Manager.getRole();
        this.role = RoleEnum.Manager.getRole();
    }

    public ShiroUserInfo(Integer userId, String userName, String role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
