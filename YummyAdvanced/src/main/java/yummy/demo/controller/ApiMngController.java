package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.demo.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ApiMngController {
    @Autowired
    ManagerService mngService;

    @PostMapping(value = "/approve")
    @ResponseBody
    public void approve(@RequestParam int rstId) {
        mngService.approve(rstId);
    }

    @PostMapping(value = "/reject")
    @ResponseBody
    public void reject(@RequestParam int rstId) {
        mngService.reject(rstId);
    }

    @PostMapping(value = "/balance")
    @ResponseBody
    public void balance(@RequestParam int rstId) {
        mngService.balance(rstId);
    }

    @PostMapping(value = "/balanceAll")
    @ResponseBody
    public void balanceAll() {
        mngService.balanceAll();
    }
}
