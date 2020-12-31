package mybatis.domain;

import java.io.Serializable;

public class Account implements Serializable {
    public Long id;//自定义mybatis中，id获取到的类型是Long ，不知道为啥
    public Integer balance;
    public String username;
    public Integer age;
    public String address;
    public String password;
    public Integer goods_id;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
