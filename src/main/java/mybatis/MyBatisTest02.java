package mybatis;

import mybatis.annotation.IAnnotationAccountDao2;
import mybatis.domain.*;
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

public class MyBatisTest02 {
    IAnnotationAccountDao2 accountDao;
    InputStream inputStream;
    SqlSession sqlSession;
    @Before
    public void init() {
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            //如果不设置参数或者参数为false就是手动提交事务，参数设置为true就是自动提交事务
            //自动提交模式下，单个sql语句会作为单个事务自动提交
            //手动提交模式在最后必须调用sqlSession.commit()方法完成事务的提交，否则更新、删除、插入无法完成
            sqlSession = sqlSessionFactory.openSession();
            accountDao = sqlSession.getMapper(IAnnotationAccountDao2.class);
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
        List<Account2> accounts = accountDao.findAll();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
        }
    }

    @Test
    public void testSelectById() {
        Account2 account2 = accountDao.findById(1);
        System.out.println(account2.toString());
    }

    @Test
    public void testInsert() {
        Account2 account2 = new Account2();
        account2.setUserName("lisa");
        account2.setAddress("西安市");
        account2.setUserAge("30");
        account2.setUserPassword("123456");
        account2.setUserBalance("3000");
        int result = accountDao.saveAccount(account2);
        System.out.println(result);
    }

    @Test
    public void testInsert2() {
        Account4 account2 = new Account4();
        account2.setUserName("lisa1");
        account2.setAddress("西安市");
        account2.setUserAge("30");
        account2.setUserPassword("123456");
        account2.setUserBalance("3000");
        accountDao.saveAccount2(account2);
        System.out.println(account2.getUserId());
    }

    @Test
    public void testUpdate() {
        Account4 account2 = new Account4();
        account2.setUserId(7);
        account2.setUserName("lisa1");
        account2.setAddress("西安市1");
        account2.setUserAge("30");
        account2.setUserPassword("123456");
        account2.setUserBalance("3000");
        accountDao.updateAccount(account2);
    }

    @Test
    public void deleteAccount() {
        accountDao.deleteAccount(6);
    }

    @Test
    public void testTotal() {
        int total = accountDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void testLikeSelect() {
        String name = "%张%";
        List<Account4> accounts = accountDao.findByName(name);;
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
        }
    }
}
