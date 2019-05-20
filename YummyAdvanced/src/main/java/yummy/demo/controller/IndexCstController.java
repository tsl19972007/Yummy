package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.demo.model.Customer;
import yummy.demo.model.Order;
import yummy.demo.model.Restaurant;
import yummy.demo.service.*;
import yummy.demo.statistics.CustomerStatistics;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexCstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    CustomerOrderService cstOrderService;

    @RequestMapping("/cstHome")
    public String cstHome(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("customer",cst);
        return "customer/cstHome";
    }

    @RequestMapping("/cstStatistics")
    public String cstStatistics(HttpServletRequest request,Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("cst",cst);
        CustomerStatistics cstStatistics=cstOrderService.getCustomerStatistics(cstId);
        model.addAttribute("cstStatistics",cstStatistics);
        return "customer/cstStatistics";
    }

    @RequestMapping("/cstOrder")
    public String cstOrder(Model model) {
        List<Restaurant> rstList=rstService.getAllRestaurants();
        model.addAttribute("rstList",rstList);
        return "customer/cstOrder";
    }

    @RequestMapping("/cstOrder/{rstId}")
    public String cstOrderRst(HttpServletRequest request, @PathVariable Integer rstId, Model model) {
        Restaurant rst=rstService.findById(rstId);
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("rst",rst);
        model.addAttribute("customer",cst);
        return "customer/cstOrderRst";
    }

    @RequestMapping("/cstOrderList")
    public String cstOrderList(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        List<Order> orderList=cstOrderService.findByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListUnpaid")
    public String cstOrderListUnpaid(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        List<Order> orderList=cstOrderService.findUnpaidByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListPaid")
    public String cstOrderListPaid(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        List<Order> orderList=cstOrderService.findPaidByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListCompleted")
    public String cstOrderListCompleted(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        List<Order> orderList=cstOrderService.findCompletedByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListReturned")
    public String cstOrderListReturned(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        List<Order> orderList=cstOrderService.findReturnedByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderDetail/{orderId}")
    public String cstOrderDetail(HttpServletRequest request,@PathVariable Integer orderId, Model model) {
        Order order=cstOrderService.findById(orderId);
        model.addAttribute("order",order);
        Restaurant rst=rstService.findById(order.getRstId());
        model.addAttribute("rst",rst);
        Customer cst=cstService.findById(order.getCstId());
        model.addAttribute("cst",cst);
        if(order.getState().equals("待支付")){
            return "customer/cstOrderPay";
        }
        return "customer/cstOrderDetail";
    }

    @RequestMapping("/cstInfo")
    public String cstInfo(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession(false).getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("customer",cst);
        model.addAttribute("level",cst.getLevel());
        model.addAttribute("percent",cst.getInfoCompletePercent());
        return "customer/cstInfo";
    }
}
