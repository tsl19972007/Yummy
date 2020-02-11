package yummy.advanced.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import yummy.advanced.config.shiro.ShiroUtil;
import yummy.advanced.dto.CustomerDTO;
import yummy.advanced.dto.OrderDTO;
import yummy.advanced.dto.OrderItemDTO;
import yummy.advanced.model.Order;
import yummy.advanced.model.OrderItem;
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RequiresRoles("Customer")
@RestController
@RequestMapping("/customer")
public class ApiCstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    @Qualifier("OrderServiceImplWithThreadPoolCancel")
    OrderService orderService;

    @PostMapping(value = "/update")
    public void update(@RequestBody CustomerDTO cstDTO) {
        cstService.update(cstDTO.toCustomer());
    }

    @PostMapping(value = "/writeOff")
    public void writeOff() {
        int cstId = ShiroUtil.getUserId();
        cstService.writeOff(cstId);
        ShiroUtil.logout();
    }

    @PostMapping(value = "/order")
    public int order(@RequestBody OrderDTO orderDTO) {
        Order order = orderDTO.toOrder();
        List<OrderItem> newItemList = new ArrayList<>();
        for (int i = 0; i < order.getItemList().size(); i++) {
            OrderItemDTO item = orderDTO.getItemList().get(i);
            newItemList.add(new OrderItem(item.getMenuItemId(), item.getType(), item.getName(), item.getPrice(), item.getNum()));
        }
        order.setItemList(newItemList);
        return orderService.add(order);
    }

    @PostMapping(value = "/cancelOrder")
    public void cancelOrder(@RequestParam int id) {
        orderService.cancelOrder(id);
    }

    @PostMapping(value = "/payOrder")
    public boolean payOrder(@RequestParam int id) {
        return orderService.payOrder(id);
    }

    @PostMapping(value = "/returnOrder")
    public double returnOrder(@RequestParam int id) {
        return orderService.returnOrder(id);
    }

    @PostMapping(value = "/finishOrder")
    public void finishOrder(@RequestParam int id) {
        orderService.finishOrder(id);
    }

    @PostMapping(value = "/getDiscount")
    public double getDiscount(@RequestParam int cstId, @RequestParam double totalPrice) {
        return orderService.getDiscount(cstId, totalPrice);
    }
}