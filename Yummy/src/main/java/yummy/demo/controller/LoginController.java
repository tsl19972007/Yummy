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
public class LoginController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    ManagerService mngService;

    @RequestMapping(value = "/cstRegister", method = RequestMethod.POST)
    @ResponseBody
    public boolean cstRegister(@RequestBody Customer cst) {
        Customer cstFound=cstService.findByEmail(cst.getEmail());
        if(cstFound!=null){
            return false;
        }
        cstService.register(cst);
        return true;
    }

    @RequestMapping(value = "/rstRegister", method = RequestMethod.POST)
    @ResponseBody
    public int rstRegister(@RequestBody Restaurant rst) {
        return rstService.register(rst);
    }

    @RequestMapping(value = "/cstLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean cstLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {
        Customer cst=cstService.login(email,password);
        if(cst!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("cstId", cst.getId());
        }
        return cst!=null;
    }

    @RequestMapping(value = "/rstLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean rstLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(rstService.login(id,password)!=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("rstId", id);
        }
        return rstService.login(id,password)!=null;
    }

    @RequestMapping(value = "/mngLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean mngLogin(HttpServletRequest request, @RequestParam int id,@RequestParam String password) {
        if(mngService.login(id,password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("mngId", id);
        }
        return mngService.login(id,password);
    }

    @RequestMapping(value = "/cstLogout", method = RequestMethod.POST)
    @ResponseBody
    public void cstLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("cstLogout");
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("/yummy/cstLogin");
    }

    @RequestMapping(value = "/rstLogout", method = RequestMethod.POST)
    @ResponseBody
    public void rstLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("rstLogout");
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("/yummy/rstLogin");
    }


}
