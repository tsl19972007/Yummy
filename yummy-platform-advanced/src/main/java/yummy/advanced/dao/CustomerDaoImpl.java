package yummy.advanced.dao;

import org.springframework.stereotype.Repository;
import yummy.advanced.model.Customer;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/1 15:32
 * @description：implementation of customerDao
 */

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
    @Override
    public Customer findByEmail(String email) {
        return getUniqueResultByHQL("SELECT c FROM Customer c WHERE c.email = ?0", email);
    }
}
