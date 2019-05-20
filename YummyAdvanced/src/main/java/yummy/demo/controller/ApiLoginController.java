package yummy.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.service.CustomerService;
import yummy.demo.service.ManagerService;
import yummy.demo.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @ResponseBody
    public boolean cstRegister(@RequestBody Customer cst) {
        Customer cstFound=cstService.findByEmail(cst.getEmail());
        if(cstFound!=null){
            return false;
        }
        cstService.register(cst);
        return true;
    }

    @PostMapping(value = "/rstRegister")
    @ResponseBody
    public int rstRegister(@RequestBody Restaurant rst) {
        return rstService.register(rst);
    }

    @PostMapping(value = "/cstLogin")
    @ResponseBody
    public boolean cstLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {
        Customer cst=cstService.login(email,password);
        if(cst!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("cstId", cst.getId());
            session.setAttribute("userName",cst.getName());
        }
        return cst!=null;
    }

    @PostMapping(value = "/rstLogin")
    @ResponseBody
    public boolean rstLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(rstService.login(id,password)!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("rstId", id);
            session.setAttribute("userName",id);
        }
        return rstService.login(id,password)!=null;
    }

    @PostMapping(value = "/mngLogin")
    @ResponseBody
    public boolean mngLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(mngService.login(id,password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("mngId", id);
            session.setAttribute("userName","manager");
        }
        return mngService.login(id,password);
    }

    @PostMapping(value = "/cstLogout")
    @ResponseBody
    public void cstLogout(HttpServletRequest request, HttpServletResponse response){
        System.out.println("cstLogout");
        HttpSession session = request.getSession(false);
        System.out.println(session.getAttribute("cstId"));
        session.invalidate();
    }

    @PostMapping(value = "/rstLogout")
    @ResponseBody
    public void rstLogout(HttpServletRequest request, HttpServletResponse response){
        System.out.println("rstLogout");
        HttpSession session = request.getSession(false);
        session.invalidate();
    }

    @PostMapping(value = "/mngLogout")
    @ResponseBody
    public void mngLogout(HttpServletRequest request, HttpServletResponse response){
        System.out.println("mngLogout");
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
}
