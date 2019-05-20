package yummy.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean isActive;
    private boolean isWrittenOff;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="address", joinColumns=@JoinColumn(name="id"))
    @Column(name="address")
    private List<String> addresses;
    private double consumption;
    private double balance;

    public Customer(int id,String email,String password,String name,String phone,boolean isActive,boolean isWrittenOff,List<String> addresses,double consumption,double balance){
        this.id=id;
        this.email=email;
        this.password=password;
        this.name=name;
        this.phone=phone;
        this.isActive=isActive;
        this.isWrittenOff=isWrittenOff;
        this.addresses=addresses;
        this.consumption=consumption;
        this.balance=balance;
    }

    public Customer(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.isActive = false;
        this.isWrittenOff=false;
        this.consumption=0;
        this.balance=0;
    }

    public Customer(){ }

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

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean getIsWrittenOff() {
        return isWrittenOff;
    }

    public void setIsWrittenOff(boolean writtenOff) {
        isWrittenOff = writtenOff;
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

    public String getLevel(){
        if(consumption>1000){
            return "顶级会员";
        }else if(consumption>100){
            return "超级会员";
        }
        return "会员";
    }

    public String getNextLevel(){
        if(consumption>1000){
            return "已封顶";
        }else if(consumption>100){
            return "顶级会员";
        }else{
            return "超级会员";
        }
    }

    public double getConsumptionToNextLevel(){
        if(consumption>1000){
            return 0;
        }else if(consumption>100){
            return 1000-consumption;
        }
        return 100-consumption;
    }

    public double getLevelPercentage(){
        if(consumption>1000){
            return 0;
        }else if(consumption>100){
            return consumption/1000;
        }
        return consumption/100;
    }


    public double getInfoCompletePercent(){
        if(addresses.isEmpty())
            return 75.0;
        else
            return 100.0;
    }
}
