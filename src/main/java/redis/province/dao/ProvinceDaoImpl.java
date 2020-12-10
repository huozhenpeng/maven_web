package redis.province.dao;

import mysql.JDBCDruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.province.Province;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao{
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCDruidUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}
