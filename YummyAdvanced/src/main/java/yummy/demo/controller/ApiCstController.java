package yummy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yummy.demo.dto.CustomerDTO;
import yummy.demo.dto.OrderDTO;
import yummy.demo.dto.OrderItemDTO;
import yummy.demo.model.Order;
import yummy.demo.model.OrderItem;
import yummy.demo.service.CustomerOrderService;
import yummy.demo.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class ApiCstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    CustomerOrderService cstOrderService;

    @PostMapping(value = "/update")
    public void update(@RequestBody CustomerDTO cstDTO) {
        cstService.update(cstDTO.toCustomer());
    }

    @PostMapping(value = "/writeOff")
    public void writeOff(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession(false);
        int cstId=(Integer)session.getAttribute(ConstantField.SESSION_CUSTOMER_ID);
        cstService.writeOff(cstId);
        session.invalidate();
    }

    @PostMapping(value = "/order")
    public int order(@RequestBody OrderDTO orderDTO) {
        Order order=orderDTO.toOrder();
        List<OrderItem> newItemList=new ArrayList<>();
        for(int i=0;i<order.getItemList().size();i++){
            OrderItemDTO item=orderDTO.getItemList().get(i);
            newItemList.add(new OrderItem(item.getMenuItemId(),item.getType(),item.getName(),item.getPrice(),item.getNum()));
        }
        order.setItemList(newItemList);
        return cstOrderService.add(order);
    }

    @PostMapping(value = "/cancelOrder")
    public void cancelOrder(@RequestParam int id) {
        cstOrderService.cancelOrder(id);
    }

    @PostMapping(value = "/payOrder")
    public boolean payOrder(@RequestParam int id) {
        return cstOrderService.payOrder(id);
    }

    @PostMapping(value = "/returnOrder")
    public double returnOrder(@RequestParam int id) {
        return cstOrderService.returnOrder(id);
    }

    @PostMapping(value = "/finishOrder")
    public void finishOrder(@RequestParam int id) {
        cstOrderService.finishOrder(id);
    }

    @PostMapping(value = "/getDiscount")
    public double getDiscount(@RequestParam int cstId,@RequestParam double totalPrice) {
        return cstOrderService.getDiscount(cstId,totalPrice);
    }
}