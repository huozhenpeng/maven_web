package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
            List<Account> accounts = accountDao.findAll();
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println(accounts.get(i).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
