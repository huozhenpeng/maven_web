package mybatis;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();
    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 保存数据
     */
    Integer saveAccount(Account account);
}
