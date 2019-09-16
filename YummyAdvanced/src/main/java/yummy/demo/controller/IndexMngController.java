package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.demo.dto.CustomerDTO;
import yummy.demo.dto.RestaurantDTO;
import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;
import yummy.demo.service.CustomerService;
import yummy.demo.service.ManagerService;
import yummy.demo.service.RestaurantService;
import yummy.demo.statistics.YummyAnnualFinance;
import yummy.demo.statistics.YummyMonthlyFinance;
import yummy.demo.statistics.YummyWeeklyFinance;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        List<Customer> cstList=mngService.getAllCustomers();
        List<CustomerDTO> cstDTOList=new ArrayList<>();
        for(int i=0;i<cstList.size();i++){
            cstDTOList.add(new CustomerDTO(cstList.get(i)));
        }
        model.addAttribute("cstList",cstDTOList);
        return "manager/mngCstInfo";
    }


    @RequestMapping("/mngRstInfo")
    public String mngRstInfo(Model model, HttpServletRequest request) {
        List<Restaurant> rstList=mngService.getAllRestaurants();
        List<RestaurantDTO> rstDTOList=new ArrayList<>();
        for(int i=0;i<rstList.size();i++){
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList",rstDTOList);
        return "manager/mngRstInfo";
    }

    @RequestMapping("/mngBalance")
    public String mngBalance(Model model) {
        List<Restaurant> rstList=mngService.getRstBalanceList();
        List<RestaurantDTO> rstDTOList=new ArrayList<>();
        for(int i=0;i<rstList.size();i++){
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList",rstDTOList);
        return "manager/mngBalance";
    }

    @RequestMapping("/mngApprove")
    public String mngApprove(Model model) {
        List<Restaurant> rstList=mngService.getRstUpdateList();
        List<RestaurantDTO> rstDTOList=new ArrayList<>();
        for(int i=0;i<rstList.size();i++){
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList",rstDTOList);
        return "manager/mngApprove";
    }

    @RequestMapping("/mngApprove/{rstId}")
    public String mngApproveDetail(@PathVariable Integer rstId, Model model) {
        RestaurantDTO newRst=new RestaurantDTO(mngService.getApplyFromId(rstId));
        RestaurantDTO oldRst=new RestaurantDTO(rstService.findById(rstId));
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
