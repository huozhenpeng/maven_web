package mybatis.domain;

import java.io.Serializable;
import java.util.List;

public class Account3 implements Serializable {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAge;
    private String address;
    private String userBalance;


    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    @Override
    public String toString() {
        return "Account3{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAge='" + userAge + '\'' +
                ", address='" + address + '\'' +
                ", userBalance='" + userBalance + '\'' +
                ", orders=" + orders +
                '}';
    }
}
