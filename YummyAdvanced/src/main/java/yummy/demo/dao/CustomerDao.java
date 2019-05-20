package yummy.demo.dao;
import yummy.demo.model.Customer;
import yummy.demo.model.Restaurant;

import java.util.List;


public interface CustomerDao{
    public List<Customer> getAllCustomers();

    public Customer findById(int id);

    public void add(Customer cst);

    public void update(Customer cst);

    public void delete(int id);

    public Customer login(String email,String password);

    public Customer findByEmail(String email);

    public void setActive(int id);

    public void writeOff(int id);
}
