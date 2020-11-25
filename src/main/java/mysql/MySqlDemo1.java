package mysql;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySqlDemo1 {

    public static void main(String[] arr) throws Exception {
        secondDemo();

    }

    public static void firstDemo() throws SQLException {
        //注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
//        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root",
                "12345678");
        Statement statement = connection.createStatement();
        String sql = "update account set balance = 600 where id = 1";
        int count = statement.executeUpdate(sql);

        System.out.println(count);
        statement.close();
        connection.close();
    }

    public static void secondDemo() throws Exception {
        Properties properties = new Properties();
        System.out.println(MySqlDemo1.class.getClassLoader().getResource("./").getPath());
        InputStream inputStream = MySqlDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update account set balance = 700 where id = 1";
        int count = statement.executeUpdate(sql);

        System.out.println(count);
        statement.close();
        connection.close();
    }




}
