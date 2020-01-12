package yummy.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yummy.old.model.Menu;
import yummy.old.model.Restaurant;
import yummy.old.service.RestaurantOrderService;
import yummy.old.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService rstService;
    @Autowired
    RestaurantOrderService rstOrderService;

    @RequestMapping(value = "/updateRst", method = RequestMethod.POST)
    @ResponseBody
    public void updateRst(@RequestBody Restaurant rst) {
        rstService.updateRst(rst);
    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    @ResponseBody
    public void updateMenu(HttpServletRequest request, @RequestBody Menu menu) {
        if(request.getSession().getAttribute("rstId")==null){
            return;
        }
        int rstId=(Integer)request.getSession().getAttribute("rstId");
        rstService.updateMenu(rstId,menu);
    }

    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    @ResponseBody
    public void finishOrder(@RequestParam int id) {
        rstOrderService.finishOrder(id);
    }
}
