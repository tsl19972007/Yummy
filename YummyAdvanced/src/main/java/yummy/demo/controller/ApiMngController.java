package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.service.CustomerService;
import yummy.demo.service.ManagerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ApiMngController {
    @Autowired
    ManagerService mngService;

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    @ResponseBody
    public void approve(@RequestParam int rstId) {
        mngService.approve(rstId);
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public void reject(@RequestParam int rstId) {
        mngService.reject(rstId);
    }

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    @ResponseBody
    public void balance(@RequestParam int rstId) {
        mngService.balance(rstId);
    }

    @RequestMapping(value = "/balanceAll", method = RequestMethod.POST)
    @ResponseBody
    public void balanceAll() {
        mngService.balanceAll();
    }
}
