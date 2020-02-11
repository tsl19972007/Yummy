package yummy.advanced.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.advanced.dto.CustomerDTO;
import yummy.advanced.dto.RestaurantDTO;
import yummy.advanced.model.Customer;
import yummy.advanced.model.Restaurant;
import yummy.advanced.service.CustomerService;
import yummy.advanced.service.ManagerService;
import yummy.advanced.service.RestaurantService;
import yummy.advanced.service.StatisticsService;
import yummy.advanced.statistics.YummyAnnualFinance;
import yummy.advanced.statistics.YummyMonthlyFinance;
import yummy.advanced.statistics.YummyWeeklyFinance;

import java.util.ArrayList;
import java.util.List;

@RequiresRoles("Manager")
@Controller
public class IndexMngController {
    @Autowired
    RestaurantService rstService;
    @Autowired
    CustomerService cstService;
    @Autowired
    ManagerService mngService;
    @Autowired
    StatisticsService staService;

    @RequestMapping("/mngHome")
    public String mngHome() {
        return "manager/mngHome";
    }

    @RequestMapping("/mngCstInfo")
    public String mngCstInfo(Model model) {
        List<Customer> cstList = cstService.getAll();
        List<CustomerDTO> cstDTOList = new ArrayList<>();
        for (int i = 0; i < cstList.size(); i++) {
            cstDTOList.add(new CustomerDTO(cstList.get(i)));
        }
        model.addAttribute("cstList", cstDTOList);
        return "manager/mngCstInfo";
    }


    @RequestMapping("/mngRstInfo")
    public String mngRstInfo(Model model) {
        List<Restaurant> rstList = rstService.getAll();
        List<RestaurantDTO> rstDTOList = new ArrayList<>();
        for (int i = 0; i < rstList.size(); i++) {
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList", rstDTOList);
        return "manager/mngRstInfo";
    }

    @RequestMapping("/mngBalance")
    public String mngBalance(Model model) {
        List<Restaurant> rstList = mngService.getRstBalanceList();
        List<RestaurantDTO> rstDTOList = new ArrayList<>();
        for (int i = 0; i < rstList.size(); i++) {
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList", rstDTOList);
        return "manager/mngBalance";
    }

    @RequestMapping("/mngApprove")
    public String mngApprove(Model model) {
        List<Restaurant> rstList = mngService.getRstUpdateList();
        List<RestaurantDTO> rstDTOList = new ArrayList<>();
        for (int i = 0; i < rstList.size(); i++) {
            rstDTOList.add(new RestaurantDTO(rstList.get(i)));
        }
        model.addAttribute("rstList", rstDTOList);
        return "manager/mngApprove";
    }

    @RequestMapping("/mngApprove/{rstId}")
    public String mngApproveDetail(@PathVariable Integer rstId, Model model) {
        RestaurantDTO newRst = new RestaurantDTO(mngService.getApplyFromId(rstId));
        RestaurantDTO oldRst = new RestaurantDTO(rstService.findById(rstId));
        model.addAttribute("oldRst", oldRst);
        model.addAttribute("newRst", newRst);
        return "manager/mngApproveDetail";
    }

    @RequestMapping("/mngStatistics")
    public String rstStatistics(Model model) {
        YummyAnnualFinance annualFinance = staService.getYummyAnnualFinance();
        YummyMonthlyFinance monthlyFinance = staService.getYummyMonthlyFinance();
        YummyWeeklyFinance weeklyFinance = staService.getYummyWeeklyFinance();
        model.addAttribute("annualFinance", annualFinance);
        model.addAttribute("monthlyFinance", monthlyFinance);
        model.addAttribute("weeklyFinance", weeklyFinance);
        return "manager/mngStatistics";
    }
}
