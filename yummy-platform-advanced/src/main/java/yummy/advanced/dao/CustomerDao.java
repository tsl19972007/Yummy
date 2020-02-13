package yummy.advanced.dao;

import yummy.advanced.model.Customer;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:24
 * @description：dao interface for Customer
 */

public interface CustomerDao extends BaseDao<Customer> {
    Customer findByEmail(String email);
}
