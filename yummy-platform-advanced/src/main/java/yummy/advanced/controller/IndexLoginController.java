package yummy.advanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.advanced.service.CustomerService;

@Controller
public class IndexLoginController {
    @Autowired
    CustomerService cstService;

    @RequestMapping("/")
    public String yummy() {
        return "yummy";
    }

    @RequestMapping("/cstLogin")
    public String cstLogin() {
        return "loginAndRegister/cstLogin";
    }

    @RequestMapping("/rstLogin")
    public String rstLogin() {
        return "loginAndRegister/rstLogin";
    }

    @RequestMapping("/mngLogin")
    public String mngLogin() {
        return "loginAndRegister/mngLogin";
    }

    @RequestMapping("/cstRegister")
    public String cstRegister() {
        return "loginAndRegister/cstRegister";
    }

    @RequestMapping("/rstRegister")
    public String rstRegister() {
        return "loginAndRegister/rstRegister";
    }

    @RequestMapping("/cstRegister/{id}")
    public String cstRegisterConfirm(@PathVariable Integer id) {
        cstService.setActive(id);
        return "loginAndRegister/cstRegisterConfirm";
    }

    @RequestMapping("/rstRegister/{id}")
    public String rstRegisterConfirm(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "loginAndRegister/rstRegisterConfirm";
    }
}
