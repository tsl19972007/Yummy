package yummy.advanced.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yummy.advanced.service.ManagerService;

@RequiresRoles("Manager")
@RestController
@RequestMapping("/manager")
public class ApiMngController {
    @Autowired
    ManagerService mngService;

    @PostMapping(value = "/approve")
    public void approve(@RequestParam int rstId) {
        mngService.approve(rstId);
    }

    @PostMapping(value = "/reject")
    public void reject(@RequestParam int rstId) {
        mngService.reject(rstId);
    }

    @PostMapping(value = "/balance")
    public void balance(@RequestParam int rstId) {
        mngService.balance(rstId);
    }

    @PostMapping(value = "/balanceAll")
    public void balanceAll() {
        mngService.balanceAll();
    }
}
