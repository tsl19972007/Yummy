package yummy.advanced.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.advanced.config.shiro.CustomizedToken;
import yummy.advanced.config.shiro.RoleEnum;
import yummy.advanced.config.shiro.ShiroUtil;
import yummy.advanced.dto.CustomerDTO;
import yummy.advanced.dto.RestaurantDTO;
import yummy.advanced.model.Customer;
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.ManagerService;
import yummy.advanced.service.RestaurantService;

@RestController
@RequestMapping("/loginAndRegister")
public class ApiLoginController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    ManagerService mngService;

    @PostMapping(value = "/cstRegister")
    public boolean cstRegister(@RequestBody CustomerDTO cstDTO) {
        Customer cst = cstDTO.toCustomer();
        Customer cstFound = cstService.findByEmail(cst.getEmail());
        if (cstFound != null) {
            return false;
        }
        cstService.register(cst);
        return true;
    }

    @PostMapping(value = "/rstRegister")
    public int rstRegister(@RequestBody RestaurantDTO rstDTO) {
        return rstService.register(rstDTO.toRestaurant());
    }

    @PostMapping(value = "/cstLogin")
    public boolean cstLogin(@RequestParam String email, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(email, password, RoleEnum.Customer.getRole());
        try {
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @PostMapping(value = "/rstLogin")
    public boolean rstLogin(@RequestParam int id, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(String.valueOf(id), password, RoleEnum.Restaurant.getRole());
        try {
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @PostMapping(value = "/mngLogin")
    public boolean mngLogin(@RequestParam int id, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(String.valueOf(id), password, RoleEnum.Manager.getRole());
        try {
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @PostMapping(value = "/cstLogout")
    public void cstLogout() {
        ShiroUtil.logout();
    }

    @PostMapping(value = "/rstLogout")
    public void rstLogout() {
        ShiroUtil.logout();
    }

    @PostMapping(value = "/mngLogout")
    public void mngLogout() {
        ShiroUtil.logout();
    }
}
