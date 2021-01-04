package mybatis;

import mybatis.annotation.IAnnotationAccountDao;
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
import java.util.ArrayList;
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
            //自动提交模式下，单个sql语句会作为单个事务自动提交
            //手动提交模式在最后必须调用sqlSession.commit()方法完成事务的提交，否则更新、删除、插入无法完成
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
        account.setUsername("王五一");
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
        account.setUsername("%王%");
        queryVo.setAccount(account);
        List<Account> accounts = accountDao.findByVo(queryVo);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }


    @Test
    public void testDynamicIf() {
        Account account = new Account();
        account.setUsername("%王%");
        account.setAddress("%市%");
        List<Account> accounts = accountDao.selectByDynamicIf(account);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }

    @Test
    public void testDynamicWhere() {
        Account account = new Account();
        account.setUsername("%王%");
        account.setAddress("%市%");
        List<Account> accounts = accountDao.selectByDynamicWhere(account);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }

    /**
     * 传入多个 id 查询用户信息，用下边两个 sql 实现：
     * SELECT * FROM USERS WHERE username LIKE '%张%' AND (id =10 OR id =89 OR id=16)
     * SELECT * FROM USERS WHERE username LIKE '%张%' AND id IN (10,89,16)
     * 这样我们在进行范围查询时，就要将一个集合中的值，作为参数动态添加进来。这样我们将如何进行参数的传递？
     */
    @Test
    public void testDynamicForeach() {
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(20);
        queryVo.setIds(ids);
        List<Account> accounts = accountDao.queryByIn(queryVo);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }


    /**
     * 用户表account   订单表goods_order
     * 查询所有订单信息，关联查询订单关联的用户信息
     *
     *  一对一、多对一
     *  因为一个订单只能属于某个用户，所以从查询订单信息出发查询所属的用户信息为一对一查询
     *  如果从用户信息触发查询用户下的订单则为一对多查询，因为一个用户可以有多个订单
     */
    @Test
    public void testMultipleTable() {
        List<AccountOrder> accounts = accountDao.selectMultipleTable();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }

    @Test
    public void testMultipleTableMap() {
        Account2 account2 = accountDao.selectMultipleTable2();
        System.out.println(account2.toString());
    }

    /**
     * 一对多
     * 查询所有用户信息以及关联的订单信息
     * O2M 一对多
     */
    @Test
    public void testMultipleTableO2M() {
        Order2 order = accountDao.selectMultipleTableO2M();
        System.out.println(order.toString());
    }

    /**
     * 多对多查询，借助第三张表
     * https://segmentfault.com/a/1190000017369618
     * 实现Role到User的多对多
     */
    @Test
    public void testMultipleTableM2M() {
        List<Role> roles = accountDao.selectMultipleTableM2M();
        for (int i = 0; i < roles.size(); i++) {
            System.out.println(roles.get(i));
        }
    }

}
