package yummy.advanced.service;

import yummy.advanced.model.Customer;

import java.util.List;

public interface CustomerService {
    void register(Customer cst);

    void setActive(int id);

    void writeOff(int id);

    void update(Customer cst);

    Customer findByEmail(String email);

    Customer findById(int id);

    List<Customer> getAll();
}
