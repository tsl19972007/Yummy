package yummy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yummy.demo.model.Customer;
import yummy.demo.model.Order;
import yummy.demo.model.OrderItem;
import yummy.demo.service.CustomerOrderService;
import yummy.demo.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class ApiCstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    CustomerOrderService cstOrderService;

    @PostMapping(value = "/update")
    @ResponseBody
    public void update(@RequestBody Customer cst) {
        cstService.update(cst);
    }

    @PostMapping(value = "/writeOff")
    @ResponseBody
    public void writeOff(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession(false);
        int cstId=(Integer)session.getAttribute("cstId");
        cstService.writeOff(cstId);
        session.invalidate();
    }

    @PostMapping(value = "/order")
    @ResponseBody
    public int order(@RequestBody Order order) {
        List<OrderItem> newItemList=new ArrayList<>();
        for(int i=0;i<order.getItemList().size();i++){
            OrderItem item=order.getItemList().get(i);
            newItemList.add(new OrderItem(item.getMenuItemId(),item.getType(),item.getName(),item.getPrice(),item.getNum()));
        }
        order.setItemList(newItemList);
        return cstOrderService.add(order);
    }

    @PostMapping(value = "/cancelOrder")
    @ResponseBody
    public void cancelOrder(@RequestParam int id) {
        cstOrderService.cancelOrder(id);
    }

    @PostMapping(value = "/payOrder")
    @ResponseBody
    public boolean payOrder(@RequestParam int id) {
        return cstOrderService.payOrder(id);
    }

    @PostMapping(value = "/returnOrder")
    @ResponseBody
    public double returnOrder(@RequestParam int id) {
        return cstOrderService.returnOrder(id);
    }

    @PostMapping(value = "/finishOrder")
    @ResponseBody
    public void finishOrder(@RequestParam int id) {
        cstOrderService.finishOrder(id);
    }

    @PostMapping(value = "/getDiscount")
    @ResponseBody
    public double getDiscount(@RequestParam int cstId,@RequestParam double totalPrice) {
        return cstOrderService.getDiscount(cstId,totalPrice);
    }
}