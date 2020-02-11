package yummy.advanced.config.shiro;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/9 17:07
 * @description：enum of login type
 */

public enum RoleEnum {
    Customer("Customer"), Restaurant("Restaurant"), Manager("Manager");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setType(String role) {
        this.role = role;
    }
}
