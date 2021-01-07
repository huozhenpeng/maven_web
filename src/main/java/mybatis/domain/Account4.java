package mybatis.domain;

import java.io.Serializable;

public class Account4 implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userAge;
    private String address;
    private String userBalance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        return "Account4{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAge='" + userAge + '\'' +
                ", address='" + address + '\'' +
                ", userBalance='" + userBalance + '\'' +
                '}';
    }
}
