package mybatis.domain;

import java.io.Serializable;

public class Account2 implements Serializable {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAge;
    private String address;
    private String userBalance;
    private Integer goods_id;

    //第二种方式
    private Order order;
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
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
        return "Account2{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAge='" + userAge + '\'' +
                ", address='" + address + '\'' +
                ", userBalance='" + userBalance + '\'' +
                ", goods_id=" + goods_id +
                ", order=" + order +
                '}';
    }
}
