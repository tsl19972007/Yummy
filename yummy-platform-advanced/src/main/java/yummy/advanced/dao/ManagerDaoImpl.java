package yummy.advanced.dao;

import org.springframework.stereotype.Repository;
import yummy.advanced.model.Customer;
import yummy.advanced.model.Manager;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 0:56
 * @description：implementation of ManagerDao
 */
@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {
    @Override
    public Manager findByIdAndPassword(Integer id, String password) {
        return getUniqueResultByHQL("SELECT m FROM Manager m WHERE m.id = ?0 and m.password=?1", id, password);
    }
}
