package mybatis;

import mybatis.domain.Account;
import mybatis.domain.Account2;
import mybatis.domain.AccountOrder;
import mybatis.domain.QueryVo;

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

    /**
     * 保存数据，并且获取新保存的数据的id
     */
    Integer testSaveAccount_getId(Account account);

    /**
     * 更新数据
     * @param account
     */
    void testUpdate(Account account);

    /**
     * 根据id删除
     * @param i
     */
    void deleteById(int i);

    /**
     * 获取总记录数
     * @return
     */
    int findTotal();

    /**
     *
     */
    List<Account> findByVo(QueryVo queryVo);

    /**
     *
     * @return
     */
    List<Account2> findAllMap();

    /**
     *
     * @param account
     */
    List<Account> selectByDynamicIf(Account account);

    /**
     *
     * @param account
     * @return
     */
    List<Account> selectByDynamicWhere(Account account);

    /**
     *
     * @param queryVo
     * @return
     */
    List<Account> queryByIn(QueryVo queryVo);


    /**
     *
     */
    List<AccountOrder> selectMultipleTable();
}
