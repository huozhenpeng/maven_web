package mybatis.annotation;

import mybatis.domain.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAnnotationAccountDao {
    @Select("select * from account")
    List<Account> findAll();
}
