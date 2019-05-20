package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yummy.demo.model.Menu;
import yummy.demo.model.Restaurant;
import yummy.demo.service.RestaurantOrderService;
import yummy.demo.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/restaurant")
public class ApiRstController {
    @Autowired
    RestaurantService rstService;
    @Autowired
    RestaurantOrderService rstOrderService;

    @PostMapping(value = "/updateRst")
    @ResponseBody
    public void updateRst(@RequestBody Restaurant rst) {
        rstService.updateRst(rst);
    }

    @PostMapping(value = "/updateMenu")
    @ResponseBody
    public void updateMenu(HttpServletRequest request, @RequestBody Menu menu) {
        int rstId=(Integer)request.getSession(false).getAttribute("rstId");
        rstService.updateMenu(rstId,menu);
    }

    @PostMapping(value = "/finishOrder")
    @ResponseBody
    public void finishOrder(@RequestParam int id) {
        rstOrderService.finishOrder(id);
    }
}
