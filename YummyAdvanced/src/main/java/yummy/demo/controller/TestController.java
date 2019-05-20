package yummy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yummy.demo.model.Customer;
import yummy.demo.model.Menu;
import yummy.demo.model.MenuItem;
import yummy.demo.model.Restaurant;
import yummy.demo.service.CustomerService;
import yummy.demo.service.RestaurantService;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;




@Controller
public class TestController {
    @Autowired
    CustomerService cstService;
    @Autowired
    RestaurantService rstService;

    @Autowired
    private JavaMailSender mailSender;


    @RequestMapping("/test")
    public String test() {
        System.out.println("test");
        //sendMailTest();
        return "yummy";
    }



    private void sendMailTest (){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            int id=123;
            helper.setFrom("498656288@qq.com");
            helper.setTo("498656288@qq.com");
            helper.setSubject("YUMMY注册验证");
            helper.setText("<html><body><p>点击<a href=\"http://localhost:8080/yummy/cstRegister/" +String.valueOf(id)+ "\">链接</a>完成邮件认证</p></body></html>", true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    private void testMenuUpdate(){
        MenuItem item = new MenuItem("套餐","item4", 10, 100);
        List<MenuItem> itemList = new ArrayList<MenuItem>();
        itemList.add(item);
        Menu menu=new Menu(null,null,itemList);
        rstService.updateMenu(1,menu);
    }

    private void testRstSave() {
        MenuItem item = new MenuItem("套餐","item1", 10, 100);
        List<MenuItem> itemList = new ArrayList<MenuItem>();
        itemList.add(item);
        Menu menu = new Menu(null, null, itemList);
        Restaurant rst = new Restaurant("1", "2", "3", "4","5");
        rst.setMenu(menu);
        rstService.register(rst);
    }

    private void testRstUpdate() {
        MenuItem item = new MenuItem("套餐","item2", 10, 100);
        item.setId(2);
        List<MenuItem> itemList = new ArrayList<MenuItem>();
        itemList.add(item);
        Menu menu = new Menu(null, null, itemList);
        menu.setId(2);
        Restaurant rst = new Restaurant("1", "2", "3", "5","6");
        rst.setId(2);
        rst.setMenu(menu);
        rstService.updateRst(rst);
    }

    private void testRstGet() {
        Restaurant rst = rstService.findById(2);
        System.out.println(rst.getId());
        System.out.println(rst.getMenu().getId());
        System.out.println(rst.getMenu().getItemList().get(0).getId());
        rst.setPhone("6");
        rstService.updateRst(rst);
    }

    private void testAddress(){
        Customer cst=new Customer("1","2","3","4");
        cst.setId(1);
        List<String> addresses=new ArrayList<String>();
        addresses.add("鼓楼");
        addresses.add("软件学院");
        cst.setAddresses(addresses);
        cstService.update(cst);
    }
}
  