package yummy.advanced.dao;

import yummy.advanced.model.Manager;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/2 0:55
 * @description：dao interface of manager
 */

public interface ManagerDao extends BaseDao<Manager> {
    Manager findByIdAndPassword(Integer id, String password);
}
