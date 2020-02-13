package yummy.advanced.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import yummy.advanced.YummyAdvancedApplication;
import yummy.advanced.dao.CustomerDao;
import yummy.advanced.model.Customer;
import yummy.advanced.service.CustomerService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyAdvancedApplication.class)
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDaoImpl;
    @Autowired
    private CustomerService customerServiceImpl;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd() {
        System.out.println("testAdd");
        Customer cst=new Customer();
        cst.setName("tslsss");
        customerDaoImpl.add(cst);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate() {
        System.out.println("testUpdate");
        customerServiceImpl.setActive(11);
    }

    @Test
    public void testFind() {
        System.out.println("testFind");
    }

    @Test
    public void testDelete() {
        System.out.println("testDelete");
    }

}
