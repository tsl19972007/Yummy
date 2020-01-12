package yummy.advanced.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        cstDao.add(cst);
        mailService.cstRegisterConfirm(cst.getId(), cst.getEmail());
    }

    @Override
    public void setActive(int id) {
        cstDao.setActive(id);
    }


    @Override
    public void writeOff(int id) {
        cstDao.deleteById(id);
    }

    @Override
    public void update(Customer cst) {
        cstDao.update(cst);
    }

    @Override
    public Customer login(String email, String password) {
        Customer cst = cstDao.findByEmailAndPassword(email, password);
        if (cst != null && cst.getIsActive() && !cst.getIsWrittenOff()) {
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
        return cstDao.get(id);
    }

    @Override
    public List<Customer> getAll() {
        return cstDao.getAll();
    }
}
