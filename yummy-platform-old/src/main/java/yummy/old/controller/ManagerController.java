package yummy.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.old.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
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
