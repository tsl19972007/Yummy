package yummy.old.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yummy.old.model.Customer;
import yummy.old.model.Order;
import yummy.old.model.OrderItem;
import yummy.old.service.CustomerOrderService;
import yummy.old.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService cstService;

    @Autowired
    CustomerOrderService cstOrderService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestBody Customer cst) {
        cstService.update(cst);
    }

    @RequestMapping(value = "/writeOff", method = RequestMethod.POST)
    @ResponseBody
    public void writeOff(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session=request.getSession(false);
        if(session.getAttribute("cstId")==null){
            return;
        }
        int cstId=(Integer)request.getSession().getAttribute("cstId");
        cstService.delete(cstId);
        session.invalidate();
        response.sendRedirect("/yummy/cstLogin");
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public int order(@RequestBody Order order) {
        List<OrderItem> newItemList=new ArrayList<OrderItem>();
        for(int i=0;i<order.getItemList().size();i++){
            OrderItem item=order.getItemList().get(i);
            newItemList.add(new OrderItem(item.getMenuItemId(),item.getName(),item.getPrice(),item.getNum()));
        }
        order.setItemList(newItemList);
        return cstOrderService.add(order);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public void cancelOrder(@RequestParam int id) {
        cstOrderService.cancelOrder(id);
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    @ResponseBody
    public boolean payOrder(@RequestParam int id) {
        return cstOrderService.payOrder(id);
    }

    @RequestMapping(value = "/returnOrder", method = RequestMethod.POST)
    @ResponseBody
    public double returnOrder(@RequestParam int id) {
        return cstOrderService.returnOrder(id);
    }

    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    @ResponseBody
    public void finishOrder(@RequestParam int id) {
        cstOrderService.finishOrder(id);
    }
}