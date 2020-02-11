package yummy.advanced.config.shiro;

import org.apache.shiro.SecurityUtils;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/9 23:30
 * @description：util of shiro
 */

public class ShiroUtil {
    public static int getUserId() {
        return ((ShiroUserInfo) SecurityUtils.getSubject().getPrincipal()).getUserId();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
