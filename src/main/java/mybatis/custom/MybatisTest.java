package mybatis.custom;


import mybatis.domain.Account;
import mybatis.IAccountDao;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IAccountDao userDao = session.getMapper(IAccountDao.class);
        //5.使用代理对象执行方法
        List<Account> users = userDao.findAll();
        for(Account user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
