package yummy.demo.dto;

import yummy.demo.model.Customer;

import java.util.List;

public class CustomerDTO {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean isActive;
    private boolean isWrittenOff;
    private List<String> addresses;
    private double consumption;
    private double balance;

    public CustomerDTO(Customer cst) {
        this.id = cst.getId();
        this.email = cst.getEmail();
        this.password = cst.getPassword();
        this.name = cst.getName();
        this.phone = cst.getPhone();
        this.isActive = cst.getIsActive();
        this.isWrittenOff = cst.getIsWrittenOff();
        this.addresses = cst.getAddresses();
        this.consumption = cst.getConsumption();
        this.balance = cst.getBalance();
    }

    public CustomerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsWrittenOff() {
        return isWrittenOff;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getConsumptionToNextLevel() {
        if (consumption > 1000) {
            return 0;
        } else if (consumption > 100) {
            return 1000 - consumption;
        }
        return 100 - consumption;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setWrittenOff(boolean writtenOff) {
        isWrittenOff = writtenOff;
    }

    public String getLevel() {
        if (consumption > 1000) {
            return "顶级会员";
        } else if (consumption > 100) {
            return "超级会员";
        }
        return "会员";
    }

    public String getNextLevel() {
        if (consumption > 1000) {
            return "已封顶";
        } else if (consumption > 100) {
            return "顶级会员";
        } else {
            return "超级会员";
        }
    }

    public double getLevelPercentage() {
        if (consumption > 1000) {
            return 0;
        } else if (consumption > 100) {
            return consumption / 1000;
        }
        return consumption / 100;

    }

    public double getInfoCompletePercent() {
        if (addresses.isEmpty())
            return 75.0;
        else
            return 100.0;
    }

    public Customer toCustomer() {
        return new Customer(id, email, password, name, phone, isActive, isWrittenOff, addresses, consumption, balance);
    }
}
