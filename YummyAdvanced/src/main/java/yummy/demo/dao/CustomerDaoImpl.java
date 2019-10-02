package yummy.demo.dao;

import org.springframework.stereotype.Repository;
import yummy.demo.model.Customer;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:32
 * @description：implementation of customerDao
 */

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
    @Override
    public Customer findByEmail(String email) {
        return getUniqueResultByHQL("SELECT c FROM Customer c WHERE c.email = ?0",email);
    }

    @Override
    public Customer findByEmailAndPassword(String email, String password) {
        return getUniqueResultByHQL("SELECT c FROM Customer c WHERE c.email = ?0 and c.password=?1",email,password);
    }

    @Override
    public void setActive(int id) {
        Customer cst=get(id);
        if(cst==null) return;
        cst.setIsActive(true);
        update(cst);
    }

    @Override
    public void setWrittenOff(int id) {
        Customer cst=get(id);
        if(cst==null) return;
        cst.setIsWrittenOff(true);
        update(cst);
    }
}
