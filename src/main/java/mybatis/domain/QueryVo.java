package mybatis.domain;

import mybatis.domain.Account;

import java.io.Serializable;

public class QueryVo implements Serializable {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
