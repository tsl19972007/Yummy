package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.service.*;
import yummy.demo.statistics.YummyAnnualFinance;
import yummy.demo.statistics.YummyMonthlyFinance;
import yummy.demo.statistics.YummyWeeklyFinance;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class IndexMngController {
    @Autowired
    RestaurantService rstService;
    @Autowired
    CustomerService cstService;
    @Autowired
    ManagerService mngService;

    @RequestMapping("/mngHome")
    public String mngHome(HttpServletRequest request) {
        return "manager/mngHome";
    }

    @RequestMapping("/mngCstInfo")
    public String mngCstInfo(Model model, HttpServletRequest request) {
        ArrayList<Customer> cstList=mngService.getAllCustomers();
        model.addAttribute("cstList",cstList);
        return "manager/mngCstInfo";
    }


    @RequestMapping("/mngRstInfo")
    public String mngRstInfo(Model model, HttpServletRequest request) {
        ArrayList<Restaurant> rstList=mngService.getAllRestaurants();
        model.addAttribute("rstList",rstList);
        return "manager/mngRstInfo";
    }

    @RequestMapping("/mngBalance")
    public String mngBalance(Model model) {
        ArrayList<Restaurant> rstList=mngService.getRstBalanceList();
        model.addAttribute("rstList",rstList);
        return "manager/mngBalance";
    }

    @RequestMapping("/mngApprove")
    public String mngApprove(Model model) {
        ArrayList<Restaurant> rstList=mngService.getRstUpdateList();
        model.addAttribute("rstList",rstList);
        return "manager/mngApprove";
    }

    @RequestMapping("/mngApprove/{rstId}")
    public String mngApproveDetail(@PathVariable Integer rstId, Model model) {
        Restaurant newRst=mngService.getApplyFromId(rstId);
        Restaurant oldRst=rstService.findById(rstId);
        model.addAttribute("oldRst",oldRst);
        model.addAttribute("newRst",newRst);
        return "manager/mngApproveDetail";
    }

    @RequestMapping("/mngStatistics")
    public String rstStatistics(Model model) {
        YummyAnnualFinance annualFinance=mngService.getYummyAnnualFinance();
        YummyMonthlyFinance monthlyFinance=mngService.getYummyMonthlyFinance();
        YummyWeeklyFinance weeklyFinance=mngService.getYummyWeeklyFinance();
        model.addAttribute("annualFinance",annualFinance);
        model.addAttribute("monthlyFinance",monthlyFinance);
        model.addAttribute("weeklyFinance",weeklyFinance);
        return "manager/mngStatistics";
    }
}
