package yummy.demo.service;

import yummy.demo.model.Customer;

public interface  CustomerService {
    public void register(Customer cst);

    public void setActive(int id);

    public void writeOff(int id);

    public void update(Customer cst);

    public void delete(int id);

    public Customer login(String email,String password);

    public Customer findByEmail(String email);

    public Customer findById(int id);
}
