package mysql;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCTemplateDemo {

    JdbcTemplate jdbcTemplate;
    @Before
    public void main() {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(JDBCDruidUtils.getDataSource());
    }

    @Test
    public void testUpdate() {
        String sql = "update account set balance = 1100 where id = 1";
        int count = jdbcTemplate.update(sql);
        System.out.println("update影响行数:"+count);
    }

    @Test
    public void insert() {
        String sql = "insert into account (balance, username, age, address) values (?, ?, ?, ?)";
        int count = jdbcTemplate.update(sql, 1000, "张三", 20, "北京市");
        System.out.println("insert影响行数:"+count);
    }

    @Test
    public void delete() {
        String sql = "delete from account where id = ?";
        int count = jdbcTemplate.update(sql, 1);
        System.out.println("delete影响行数："+count);
    }

    @Test
    public void  select2Map() {
        String sql = "select * from account where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 2);
        System.out.println(map);
        //{id=2, balance=1000, username=张三, age=20, address=北京市}
    }

    @Test
    public void selectAll2List() {
        String sql = "select * from account";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //{id=2, balance=1000, username=张三, age=20, address=北京市}
        //{id=3, balance=2000, username=李四, age=30, address=上海市}
    }

    @Test
    public void select2Person() {
        String sql = "select * from account";
        List<Person> persons = jdbcTemplate.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person();
                person.address = resultSet.getString("address");
                person.age = resultSet.getInt("age");
                person.id = resultSet.getInt("id");
                person.username = resultSet.getString("username");
                return person;
            }
        });
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }
        //Person{userName='张三', age=20, address='北京市', id=2}
        //Person{userName='李四', age=30, address='上海市', id=3}
    }

    @Test
    public void select2Person02() {
        String sql = "select * from account";
        //实体类字段名称需要与数据库字段名称保持一致
        List<Person> persons = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class));
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }
        //Person{username='张三', age=20, address='北京市', id=2}
        //Person{username='李四', age=30, address='上海市', id=3}
    }

    @Test
    public void selectCount() {
        String sql = "select count(id) from account";
        Long total = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println("记录总数:"+total);
        //记录总数:2
    }



}
