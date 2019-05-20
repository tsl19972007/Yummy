package yummy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import yummy.demo.dao.BaseDao;
import yummy.demo.dao.CustomerDao;



@SpringBootApplication
@ComponentScan("yummy.demo")
@EntityScan("yummy.demo.model")
public class DemoApplication {
    @Autowired
    private CustomerDao cstDao;
    @Autowired
    private BaseDao baseDao;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private void test(){
        System.out.println(cstDao==null);
        System.out.println(null==cstDao.findById(1));
    }
}

