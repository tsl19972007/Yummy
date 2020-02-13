package yummy.advanced.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import yummy.advanced.model.Customer;
import yummy.advanced.model.Manager;
import yummy.advanced.model.Restaurant;
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.ManagerService;
import yummy.advanced.service.RestaurantService;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/9 16:38
 * @description：realm of customer
 */

public class UserRealm extends AuthorizingRealm {
    /*
    配置懒加载，否则与Spring初始化冲突，导致@Transactional不可用
     */
    @Autowired
    @Lazy
    CustomerService cstService;
    @Autowired
    @Lazy
    RestaurantService rstService;
    @Autowired
    @Lazy
    ManagerService mngService;

    /*
    分身份用户的授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUserInfo shiroUserInfo = (ShiroUserInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(shiroUserInfo.getRole());
        return info;
    }

    /*
    分身份用户的认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomizedToken token = (CustomizedToken) authenticationToken;
        String role = token.getRole();
        if (RoleEnum.Customer.getRole().equals(role)) {
            Customer cst = cstService.findByEmail(token.getUsername());
            if (cst == null || !cst.getState().equals(CustomerStateEnum.Registered.getState())) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            ByteSource salt=ByteSource.Util.bytes(cst.getEmail());
            return new SimpleAuthenticationInfo(new ShiroUserInfo(cst), cst.getPassword(), salt, getName());
        } else if (RoleEnum.Restaurant.getRole().equals(role)) {
            Restaurant rst = rstService.findById(Integer.parseInt(token.getUsername()));
            if (rst == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            ByteSource salt=ByteSource.Util.bytes(rst.getPhone());
            return new SimpleAuthenticationInfo(new ShiroUserInfo(rst), rst.getPassword(), salt, getName());
        } else if (RoleEnum.Manager.getRole().equals(role)) {
            Manager mng = mngService.login(Integer.parseInt(token.getUsername()), String.valueOf(token.getPassword()));
            if (mng == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            return new SimpleAuthenticationInfo(new ShiroUserInfo(mng), mng.getPassword(), getName());
        } else {
            throw new UnsupportedTokenException("role不正确");
        }
    }
}
