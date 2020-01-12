package yummy.demo.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yummy.demo.dao.CustomerDao;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerDaoTest.class)
public class CustomerDaoTest {
    @Autowired
    private CustomerDao CustomerDaoImpl;

    @Test
    public void testAdd() {
        System.out.println("testAdd");
        //Customer cst=new Customer("1","2","3","4");
        //cstDao.add(cst);
    }

    @Test
    public void testUpdate() {
        System.out.println("testUpdate");
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
