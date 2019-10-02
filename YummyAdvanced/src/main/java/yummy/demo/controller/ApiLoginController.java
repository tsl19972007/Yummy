package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.demo.dto.CustomerDTO;
import yummy.demo.dto.RestaurantDTO;
import yummy.demo.model.Customer;
import yummy.demo.service.CustomerService;
import yummy.demo.service.ManagerService;
import yummy.demo.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        Customer cst=cstDTO.toCustomer();
        Customer cstFound=cstService.findByEmail(cst.getEmail());
        if(cstFound!=null){
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
    public boolean cstLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {
        Customer cst=cstService.login(email,password);
        if(cst!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute(ConstantField.SESSION_CUSTOMER_ID, cst.getId());
            session.setAttribute(ConstantField.SESSION_USERNAME, cst.getName());
        }
        return cst!=null;
    }

    @PostMapping(value = "/rstLogin")
    public boolean rstLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(rstService.login(id,password)!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute(ConstantField.SESSION_RESTAURANT_ID, id);
            session.setAttribute(ConstantField.SESSION_USERNAME,id);
        }
        return rstService.login(id,password)!=null;
    }

    @PostMapping(value = "/mngLogin")
    public boolean mngLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(mngService.login(id,password)){
            HttpSession session = request.getSession(true);
            session.setAttribute(ConstantField.SESSION_MANAGER_ID, id);
            session.setAttribute(ConstantField.SESSION_USERNAME,"manager");
        }
        return mngService.login(id,password);
    }

    @PostMapping(value = "/cstLogout")
    public void cstLogout(HttpServletRequest request){
        userLogout(request);
    }

    @PostMapping(value = "/rstLogout")
    public void rstLogout(HttpServletRequest request){
        userLogout(request);
    }

    @PostMapping(value = "/mngLogout")
    public void mngLogout(HttpServletRequest request){
        userLogout(request);
    }

    private void userLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null) return;
        session.invalidate();
    }
}
