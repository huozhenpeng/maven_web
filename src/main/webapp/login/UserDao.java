package main.webapp.login;

import main.webapp.mysql.JDBCDruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCDruidUtils.getDataSource());
    public User login(User user) {
        String sql = "select * from account where username = ? and password = ?";
        List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setPassWord(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        }, user.getUserName(), user.getPassWord());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
