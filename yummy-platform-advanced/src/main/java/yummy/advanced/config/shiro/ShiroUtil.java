package yummy.advanced.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

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

    /*
    密码+盐值md5加密三次
     */
    public static String encrypt(String originalPassword, String salt) {
        return new Md5Hash(originalPassword, salt, 3).toString();
    }
}
