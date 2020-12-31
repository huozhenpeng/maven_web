package mybatis.domain;

/**
 * 包含用户信息，同时包含订单信息
 */
public class AccountOrder extends Account {
    private String goods_name;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    @Override
    public String toString() {
        return "AccountOrder{" +
                "goods_name='" + goods_name + '\'' +
                ", id=" + id +
                ", balance=" + balance +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", goods_id=" + goods_id +
                '}';
    }
}
