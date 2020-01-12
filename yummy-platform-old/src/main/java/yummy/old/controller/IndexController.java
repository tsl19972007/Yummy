package yummy.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.old.model.Customer;
import yummy.old.model.Order;
import yummy.old.model.Restaurant;
import yummy.old.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    ManagerService mngService;
    @Autowired
    CustomerOrderService cstOrderService;
    @Autowired
    RestaurantOrderService rstOrderService;

    @RequestMapping("/")
    public String yummy() {
        return "yummy";
    }

    @RequestMapping("/cstLogin")
    public String cstLogin() {
        return "loginAndRegister/cstLogin";
    }

    @RequestMapping("/rstLogin")
    public String rstLogin() {
        return "loginAndRegister/rstLogin";
    }

    @RequestMapping("/mngLogin")
    public String mngLogin() {
        return "loginAndRegister/mngLogin";
    }

    @RequestMapping("/cstRegister")
    public String cstRegister() {
        return "loginAndRegister/cstRegister";
    }

    @RequestMapping("/rstRegister")
    public String rstRegister() {
        return "loginAndRegister/rstRegister";
    }


    @RequestMapping("/cstHome")
    public String cstHome(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("cstId")==null){
            return "yummy";
        }
        int cstId=(Integer)request.getSession().getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("customer",cst);
        return "customer/cstHome";
    }

    @RequestMapping("/rstHome")
    public String rstHome(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("rstId")==null){
            return "yummy";
        }
        int rstId=(Integer)request.getSession().getAttribute("rstId");
        Restaurant rst=rstService.findById(rstId);
        model.addAttribute("restaurant",rst);
        return "restaurant/rstHome";
    }

    @RequestMapping("/mngHome")
    public String mngHome(HttpServletRequest request) {
        if(request.getSession().getAttribute("mngId")==null){
            return "yummy";
        }
        return "manager/mngHome";
    }

    @RequestMapping("/mngApprove")
    public String mngApprove(Model model) {
        ArrayList<Restaurant> rstList=mngService.getRstUpdateList();
        model.addAttribute("rstList",rstList);
        return "manager/mngApprove";
    }

    @RequestMapping("/mngApprove/{rstId}")
    public String mngApproveDetail(@PathVariable Integer rstId,Model model) {
        Restaurant newRst=mngService.getApplyFromId(rstId);
        Restaurant oldRst=rstService.findById(rstId);
        model.addAttribute("oldRst",oldRst);
        model.addAttribute("newRst",newRst);
        return "manager/mngApproveDetail";
    }

    @RequestMapping("/mngBalance")
    public String mngBalance(Model model) {
        ArrayList<Restaurant> rstList=mngService.getRstBalanceList();
        model.addAttribute("rstList",rstList);
        return "manager/mngBalance";
    }

    @RequestMapping("/cstRegister/{id}")
    public String cstRegisterConfirm(@PathVariable Integer id){
        cstService.setActive(id);
        return "loginAndRegister/cstRegisterConfirm";
    }

    @RequestMapping("/rstRegister/{id}")
    public String rstRegisterConfirm(@PathVariable Integer id,Model model) {
        model.addAttribute("id", id);
        return "loginAndRegister/rstRegisterConfirm";
    }

    @RequestMapping("/rstInfo")
    public String rstInfo(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("rstId")==null){
            return "yummy";
        }
        int rstId=(Integer)request.getSession().getAttribute("rstId");
        Restaurant rst=rstService.findById(rstId);
        model.addAttribute("restaurant",rst);
        return "restaurant/rstInfo";
    }

    @RequestMapping("/rstMenu")
    public String rstMenu() {
        return "restaurant/rstMenu";
    }

    @RequestMapping("/rstOrderList")
    public String rstOrderList(HttpServletRequest request, Model model) {
        int rstId=(Integer)request.getSession().getAttribute("rstId");
        List<Order> orderList=rstOrderService.findByRst(rstId);
        Iterator<Order> iterator = orderList.iterator();
        while(iterator.hasNext()){
            Order order = iterator.next();
            if("unpaid".equals(order.getState())){
                iterator.remove();
            }
        }
        model.addAttribute("orderList",orderList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderDetail/{orderId}")
    public String rstOrderDetail(HttpServletRequest request,@PathVariable Integer orderId, Model model) {
        Order order=rstOrderService.findById(orderId);
        model.addAttribute("order",order);
        Customer cst=cstService.findById(order.getCstId());
        model.addAttribute("cst",cst);
        return "restaurant/rstOrderDetail";
    }

    @RequestMapping("/rstStatistics")
    public String rstStatistics() {
        return "restaurant/rstStatistics";
    }

    @RequestMapping("/cstStatistics")
    public String cstStatistics() {
        return "customer/cstStatistics";
    }

    @RequestMapping("/cstOrder")
    public String cstOrder(Model model) {
        List<Restaurant> rstList=rstService.getAllRestaurants();
        model.addAttribute("rstList",rstList);
        return "customer/cstOrder";
    }

    @RequestMapping("/cstOrder/{rstId}")
    public String cstOrderRst(HttpServletRequest request,@PathVariable Integer rstId, Model model) {
        Restaurant rst=rstService.findById(rstId);
        int cstId=(Integer)request.getSession().getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("restaurant",rst);
        model.addAttribute("customer",cst);
        return "customer/cstOrderRst";
    }

    @RequestMapping("/cstOrderList")
    public String cstOrderList(HttpServletRequest request, Model model) {
        int cstId=(Integer)request.getSession().getAttribute("cstId");
        List<Order> orderList=cstOrderService.findByCst(cstId);
        model.addAttribute("orderList",orderList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderDetail/{orderId}")
    public String cstOrderDetail(HttpServletRequest request,@PathVariable Integer orderId, Model model) {
        Order order=cstOrderService.findById(orderId);
        model.addAttribute("order",order);
        Restaurant rst=rstService.findById(order.getRstId());
        model.addAttribute("rst",rst);
        if(order.getState().equals("unpaid")){
            return "customer/cstOrderPay";
        }
        return "customer/cstOrderDetail";
    }

    @RequestMapping("/cstInfo")
    public String cstInfo(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("cstId")==null){
            return "yummy";
        }
        int cstId=(Integer)request.getSession().getAttribute("cstId");
        Customer cst=cstService.findById(cstId);
        model.addAttribute("customer",cst);
        model.addAttribute("level",cst.getLevel());
        return "customer/cstInfo";
    }
}