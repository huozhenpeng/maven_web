package main.webapp.mysql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class JDBCUtilsTest {

    @Test
    public void test() {
        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String sql = "update account set balance = 1000 where id = 1";
            int count = statement.executeUpdate(sql);
            System.out.println(count);
            JDBCUtils.close(statement, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}