package mybatis.domain;


import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable {
    private Account account;
    private List<Integer> ids;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
