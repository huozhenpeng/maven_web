package mybatis.annotation;

import mybatis.domain.Account;
import mybatis.domain.Account2;
import mybatis.domain.Account4;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IAnnotationAccountDao2 {
    @Results(
            id = "accountMap",
            value = {
                    @Result(id = true, column = "id", property = "userId"),
                    @Result(column = "username", property = "userName"),
                    @Result(column = "balance", property = "userBalance"),
                    @Result(column = "age", property = "userAge"),
                    @Result(column = "address", property = "address"),
                    @Result(column = "password", property = "userPassword")
            }
    )
    @Select("select * from account")
    List<Account2> findAll();

    @Select("select * from account where id = #{uid}")
    @ResultMap("accountMap")
    Account2 findById(int userId);


    /**
     * 保存用户
     * @return
     */
    @Insert("insert into account (username, age, address, balance, password) values (#{userName}, #{userAge}, #{address}, #{userBalance}, #{userPassword})")
    int saveAccount(Account2 account2);

    /**
     * 保存用户，并且返回新保存的账户的id（id是主键，自增长）
     * @return
     */
    @Insert("insert into account (username, age, address, balance, password) values (#{userName}, #{userAge}, #{address}, #{userBalance}, #{userPassword})")
    @SelectKey(keyColumn = "id", keyProperty = "userId", resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    int saveAccount2(Account4 account3);

    /**
     * 更新用户
     */
    @Update("update account set username = #{userName}, address = #{address}, age = #{userAge}, balance = #{userBalance}, password = #{userPassword} where id = #{userId}")
    int updateAccount(Account4 account4);

    /**
     * 删除用户
     */
    @Delete("delete from account where id = #{userId}")
    int deleteAccount(Integer userId);

    /**
     * 聚合函数使用
     */
    @Select("select count(*) from account")
    int findTotal();

    /**
     * 模糊查询
     */
    @Results(
            id = "accountMap2",
            value = {
                    @Result(id = true, column = "id", property = "userId"),
                    @Result(column = "username", property = "userName"),
                    @Result(column = "balance", property = "userBalance"),
                    @Result(column = "age", property = "userAge"),
                    @Result(column = "address", property = "address"),
                    @Result(column = "password", property = "userPassword")
            }
    )
    @Select("select * from account where username like #{name}")
    List<Account4> findByName(String name);

}
