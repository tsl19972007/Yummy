package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.demo.dto.CustomerDTO;
import yummy.demo.dto.OrderDTO;
import yummy.demo.dto.RestaurantDTO;
import yummy.demo.model.Order;
import yummy.demo.service.CustomerService;
import yummy.demo.service.RestaurantOrderService;
import yummy.demo.service.RestaurantService;
import yummy.demo.statistics.RestaurantStatistics;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexRstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    RestaurantOrderService rstOrderService;

    @RequestMapping("/rstHome")
    public String rstHome(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        RestaurantDTO rst=new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("restaurant",rst);
        return "restaurant/rstHome";
    }

    @RequestMapping("/rstInfo")
    public String rstInfo(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        RestaurantDTO rst=new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("restaurant",rst);
        return "restaurant/rstInfo";
    }

    @RequestMapping("/rstMenu")
    public String rstMenu(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        RestaurantDTO rst=new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("menu",rst.getMenuDTO());
        return "restaurant/rstMenu";
    }

    @RequestMapping("/rstOrderListPaid")
    public String rstOrderListPaid(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        List<Order> orderList=rstOrderService.findPaidByRst(rstId);
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++){
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList",orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderListCompleted")
    public String rstOrderListCompleted(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        List<Order> orderList=rstOrderService.findCompletedByRst(rstId);
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++){
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList",orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderListReturned")
    public String rstOrderListReturend(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        List<Order> orderList=rstOrderService.findReturnedByRst(rstId);
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++){
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList",orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderDetail/{orderId}")
    public String rstOrderDetail(HttpServletRequest request, @PathVariable Integer orderId, Model model) {
        OrderDTO order=new OrderDTO(rstOrderService.findById(orderId));
        model.addAttribute("order",order);
        CustomerDTO cst=new CustomerDTO(cstService.findById(order.getCstId()));
        model.addAttribute("cst",cst);
        RestaurantDTO rst=new RestaurantDTO(rstService.findById(order.getRstId()));
        model.addAttribute("rst",rst);
        return "restaurant/rstOrderDetail";
    }

    @RequestMapping("/rstStatistics")
    public String rstStatistics(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession(false).getAttribute(ConstantField.SESSION_RESTAURANT_ID);
        RestaurantStatistics rstStatistics=rstOrderService.getRestaurantStatistics(rstId);
        model.addAttribute("rstStatistics",rstStatistics);
        return "restaurant/rstStatistics";
    }
}
