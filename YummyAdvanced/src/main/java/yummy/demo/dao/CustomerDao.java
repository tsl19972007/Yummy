package yummy.demo.dao;

import yummy.demo.model.Customer;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:24
 * @description：dao interface for Customer
 */

public interface CustomerDao extends BaseDao<Customer> {
    public Customer findByEmail(String email);

    public Customer findByEmailAndPassword(String email, String password);

    public void setActive(int id);

    public void setWrittenOff(int id);
}
