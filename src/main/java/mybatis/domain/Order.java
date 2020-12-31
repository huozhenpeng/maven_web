package mybatis.domain;

import java.io.Serializable;

public class Order implements Serializable {
    private String goods_id;
    private String goods_name;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                '}';
    }
}
