package yummy.advanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import yummy.advanced.dto.MenuDTO;
import yummy.advanced.dto.RestaurantDTO;
import yummy.advanced.service.OrderService;
import yummy.advanced.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/restaurant")
public class ApiRstController {
    @Autowired
    RestaurantService rstService;
    @Autowired
    @Qualifier("OrderServiceImplWithThreadPoolCancel")
    OrderService orderService;

    @PostMapping(value = "/updateRst")
    public void updateRst(@RequestBody RestaurantDTO rstDTO) {
        rstService.updateRst(rstDTO.toRestaurant());
    }

    @PostMapping(value = "/updateMenu")
    public void updateMenu(HttpServletRequest request, @RequestBody MenuDTO menuDTO) {
        int rstId = (Integer) request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        rstService.updateMenu(rstId, menuDTO.toMenu());
    }

    @PostMapping(value = "/finishOrder")
    public void finishOrder(@RequestParam int id) {
        orderService.finishOrder(id);
    }
}
