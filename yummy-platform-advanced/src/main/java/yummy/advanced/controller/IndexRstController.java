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
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.OrderService;
import yummy.advanced.service.RestaurantService;
import yummy.advanced.service.StatisticsService;
import yummy.advanced.statistics.RestaurantStatistics;

import java.util.ArrayList;
import java.util.List;

@RequiresRoles("Restaurant")
@Controller
public class IndexRstController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;
    @Autowired
    @Qualifier("OrderServiceImplWithThreadPoolCancel")
    OrderService orderService;
    @Autowired
    StatisticsService staService;

    @RequiresRoles("Restaurant")
    @RequestMapping("/rstHome")
    public String rstHome(Model model) {
        int rstId = ShiroUtil.getUserId();
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("restaurant", rst);
        return "restaurant/rstHome";
    }

    @RequestMapping("/rstInfo")
    public String rstInfo(Model model) {
        int rstId = ShiroUtil.getUserId();
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("restaurant", rst);
        return "restaurant/rstInfo";
    }

    @RequestMapping("/rstMenu")
    public String rstMenu(Model model) {
        int rstId = ShiroUtil.getUserId();
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("menu", rst.getMenuDTO());
        return "restaurant/rstMenu";
    }

    @RequestMapping("/rstOrderListPaid")
    public String rstOrderListPaid(Model model) {
        int rstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByRst(rstId, "进行中");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderListCompleted")
    public String rstOrderListCompleted(Model model) {
        int rstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByRst(rstId, "已完成");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderListReturned")
    public String rstOrderListReturned(Model model) {
        int rstId = ShiroUtil.getUserId();
        List<Order> orderList = orderService.findByRst(rstId, "已退订");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            orderDTOList.add(new OrderDTO(orderList.get(i)));
        }
        model.addAttribute("orderList", orderDTOList);
        return "restaurant/rstOrderList";
    }

    @RequestMapping("/rstOrderDetail/{orderId}")
    public String rstOrderDetail(@PathVariable Integer orderId, Model model) {
        OrderDTO order = new OrderDTO(orderService.findById(orderId));
        model.addAttribute("order", order);
        CustomerDTO cst = new CustomerDTO(cstService.findById(order.getCstId()));
        model.addAttribute("cst", cst);
        RestaurantDTO rst = new RestaurantDTO(rstService.findById(order.getRstId()));
        model.addAttribute("rst", rst);
        return "restaurant/rstOrderDetail";
    }

    @RequestMapping("/rstStatistics")
    public String rstStatistics(Model model) {
        int rstId = ShiroUtil.getUserId();
        RestaurantStatistics rstStatistics = staService.getRestaurantStatistics(rstId);
        model.addAttribute("rstStatistics", rstStatistics);
        return "restaurant/rstStatistics";
    }
}
