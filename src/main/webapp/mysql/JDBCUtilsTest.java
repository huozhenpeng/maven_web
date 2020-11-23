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
        } catch (SQLException th) {
            th.printStackTrace();
        }
    }

    @Test
    public void testTransaction() {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "update account set balance = 2000 where id = 1";
            int count = statement.executeUpdate(sql);
            System.out.println(count);
            connection.commit();

        } catch (SQLException th) {
            th.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } finally {
            JDBCUtils.close(statement, connection);
        }
    }
}