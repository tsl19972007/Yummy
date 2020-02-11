package yummy.advanced.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/9 17:05
 * @description：username and password token with multiple role
 */

public class CustomizedToken extends UsernamePasswordToken {

    //登陆类型，具体见roleEnum
    private String role;

    public CustomizedToken(String username, String password, String role) {
        super(username, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
