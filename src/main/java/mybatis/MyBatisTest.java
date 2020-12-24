package mybatis;

import mybatis.annotation.IAnnotationAccountDao;
import mybatis.domain.Account;
import mybatis.domain.Account2;
import mybatis.domain.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    IAccountDao accountDao;
    InputStream inputStream;
    SqlSession sqlSession;
    @Before
    public void init() {
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            //如果不设置参数或者参数为false就是手动提交事务，参数设置为true就是自动提交事务
            sqlSession = sqlSessionFactory.openSession();
            accountDao = sqlSession.getMapper(IAccountDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public void onDestroy() throws IOException {
        //设置为手动提交事务后，需要最后调用commit来完成事务的提交
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void test() {
        List<Account> accounts = accountDao.findAll();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
        }
    }
    @Test
    public void findAllMap() {
        List<Account2> accounts = accountDao.findAllMap();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
        }
    }

    @Test
    public void testAnnotation() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            IAnnotationAccountDao accountDao = sqlSession.getMapper(IAnnotationAccountDao.class);
            List<Account> accounts = accountDao.findAll();
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println(accounts.get(i).toString());
            }
            sqlSession.commit();
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        Account account = accountDao.findById(3);
        System.out.println(account);
    }

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setBalance(3000);
        account.setUsername("王五");
        account.setAge(40);
        account.setAddress("深圳市");
        account.setPassword("123456");
        Integer result = accountDao.saveAccount(account);
        System.out.println(result);
    }

    @Test
    public void testSaveAccount_getId() {
        Account account = new Account();
        account.setBalance(3000);
        account.setUsername("赵六王");
        account.setAge(40);
        account.setAddress("重庆市");
        account.setPassword("123456");
        accountDao.testSaveAccount_getId(account);
        System.out.println(account.getId());
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(2l);
        account.setBalance(3000);
        account.setAge(30);
        accountDao.testUpdate(account);
    }

    @Test
    public void testDelete() {
        accountDao.deleteById(15);
    }

    @Test
    public void testFindTotal() {
        int total = accountDao.findTotal();
        System.out.println(total);
    }
    @Test
    public void testFindByVo() {
        QueryVo queryVo = new QueryVo();
        Account account = new Account();
        //注意加上单引号，要不查询出错
        account.setUsername("'%王%'");
        queryVo.setAccount(account);
        List<Account> accounts = accountDao.findByVo(queryVo);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }

}
