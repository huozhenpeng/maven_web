package mybatis.domain;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
//  1	教师	100
//  2	运动员	100
//  3	飞行员	200

    private Integer rid;
    private Integer userid;
    private String role_name;
    private List<User> users;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", userid=" + userid +
                ", role_name='" + role_name + '\'' +
                ", users=" + users +
                '}';
    }

}
