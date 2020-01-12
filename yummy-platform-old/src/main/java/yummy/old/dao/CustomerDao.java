package yummy.old.dao;
import yummy.old.model.Customer;


public interface CustomerDao{
    public Customer findById(int id);

    public void add(Customer cst);

    public void update(Customer cst);

    public void delete(int id);

    public Customer login(String email,String password);

    public Customer findByEmail(String email);

    public void setActive(int id);
}
