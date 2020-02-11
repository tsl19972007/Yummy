package yummy.advanced.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.advanced.config.shiro.ShiroUtil;
import yummy.advanced.dto.CustomerDTO;
import yummy.advanced.dto.OrderDTO;
import yummy.advanced.dto.RestaurantDTO;
import yummy.advanced.model.Order;
import yummy.advanced.model.Restaurant;
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.OrderService;
import yummy.advanced.service.RestaurantService;
import yummy.advanced.service.StatisticsService;
import yummy.advanced.statistics.CustomerStatistics;

import java.util.ArrayList;
import java.util.List;

@RequiresRoles("Customer")
@Controller
public class IndexCstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    @Qualifier("OrderServiceImplWithThreadPoolCancel")
    OrderService orderService;
    @Autowired
    StatisticsService staService;

    @RequestMapping("/cstHome")
    public String cstHome(Model model) {
        int cstId = ShiroUtil.getUserId();
        CustomerDTO cst = new CustomerDTO(cstService.findById(cstId));
        model.addAttribute("customer", cst);
        return "customer/cstHome";
    }

    @RequestMapping("/cstStatistics")
    public String cstStatistics(Model model) {
        int cstId = ShiroUtil.getUserId();
        CustomerDTO cst = new CustomerDTO(cstService.findById(cstId));
        model.addAttribute("cst", cst);
        CustomerStatistics cstStatistics = staService.getCustomerStatistics(cstId);
        model.addAttribute("cstStatistics", cstStatistics);
        return "customer/cstStatistics";
    }

    @RequestMapping("/cstOrder")
    public String cstOrder(Model model) {
        List<Restaurant> rstList = rstService.getAll();
        List<RestaurantDTO> rstDTOList = new ArrayList<>();
        for (int i = 0; i < rstList.size(); i++) {
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList", rstDTOList);
        return "customer/cstOrder";
    }

    @RequestMapping("/cstOrder/{rstId}")
    public String cstOrderRst(@PathVariable Integer rstId, Model model) {
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(rstId));
        int cstId = ShiroUtil.getUserId();
        CustomerDTO cst = new CustomerDTO(cstService.findById(cstId));
        model.addAttribute("rst", rst);
        model.addAttribute("customer", cst);
        return "customer/cstOrderRst";
    }

    @RequestMapping("/cstOrderList")
    public String cstOrderList(Model model) {
        int cstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByCst(cstId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListUnpaid")
    public String cstOrderListUnpaid(Model model) {
        int cstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByCst(cstId, "待支付");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListPaid")
    public String cstOrderListPaid(Model model) {
        int cstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByCst(cstId, "进行中");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListCompleted")
    public String cstOrderListCompleted(Model model) {
        int cstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByCst(cstId, "已完成");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderListReturned")
    public String cstOrderListReturned(Model model) {
        int cstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByCst(cstId, "已退订");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "customer/cstOrderList";
    }

    @RequestMapping("/cstOrderDetail/{orderId}")
    public String cstOrderDetail(@PathVariable Integer orderId, Model model) {
        OrderDTO order = new OrderDTO(orderService.findById(orderId));
        model.addAttribute("order", order);
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(order.getRstId()));
        model.addAttribute("rst", rst);
        CustomerDTO cst = new CustomerDTO(cstService.findById(order.getCstId()));
        model.addAttribute("cst", cst);
        if (order.getState().equals("待支付")) {
            return "customer/cstOrderPay";
        }
        return "customer/cstOrderDetail";
    }

    @RequestMapping("/cstInfo")
    public String cstInfo(Model model) {
        int cstId = ShiroUtil.getUserId();
        CustomerDTO cst = new CustomerDTO(cstService.findById(cstId));
        model.addAttribute("customer", cst);
        model.addAttribute("level", cst.getLevel());
        model.addAttribute("percent", cst.getInfoCompletePercent());
        return "customer/cstInfo";
    }
}
