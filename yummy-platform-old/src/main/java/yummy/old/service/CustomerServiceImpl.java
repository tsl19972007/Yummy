package yummy.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yummy.old.dao.CustomerDao;
import yummy.old.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDao cstDao;
    @Autowired
    MailService mailService;

    @Override
    public void register(Customer cst) {
        cst.setBalance(1000);
        cstDao.add(cst);
        mailService.cstRegisterConfirm(cst.getId(),cst.getEmail());
    }

    @Override
    public void setActive(int id) {
        cstDao.setActive(id);
    }

    @Override
    public void update(Customer cst) {
        cstDao.update(cst);
    }

    @Override
    public void delete(int id) {
        cstDao.delete(id);
    }

    @Override
    public Customer login(String email, String password) {
        Customer cst=cstDao.login(email,password);
        if(cst!=null&&cst.getIsActive()) {
            return cst;
        }
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        return cstDao.findByEmail(email);
    }

    @Override
    public Customer findById(int id) {
        return cstDao.findById(id);
    }
}
