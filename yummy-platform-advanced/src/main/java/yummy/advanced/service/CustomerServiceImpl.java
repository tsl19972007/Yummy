package yummy.advanced.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yummy.advanced.config.shiro.CustomerStateEnum;
import yummy.advanced.config.shiro.ShiroUtil;
import yummy.advanced.dao.CustomerDao;
import yummy.advanced.model.Customer;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao cstDao;
    @Autowired
    MailService mailService;

    @Override
    public void register(Customer cst) {
        //顾客用email作盐值对密码加密
        String password = ShiroUtil.encrypt(cst.getPassword(), cst.getEmail());
        cst.setPassword(password);
        cstDao.add(cst);
        mailService.cstRegisterConfirm(cst.getId(), cst.getEmail());
    }

    @Override
    public void setActive(int id) {
        Customer cst = cstDao.get(id);
        cst.setState(CustomerStateEnum.Registered.getState());
    }


    @Override
    public void writeOff(int id) {
        Customer cst = cstDao.get(id);
        cst.setState(CustomerStateEnum.Canceled.getState());
    }

    @Override
    public void update(Customer cst) {
        String password = ShiroUtil.encrypt(cst.getPassword(), cst.getEmail());
        cst.setPassword(password);
        cstDao.update(cst);
    }

    @Override
    public Customer findByEmail(String email) {
        return cstDao.findByEmail(email);
    }

    @Override
    public Customer findById(int id) {
        return cstDao.get(id);
    }

    @Override
    public List<Customer> getAll() {
        return cstDao.getAll();
    }
}
