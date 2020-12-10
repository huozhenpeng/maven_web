package redis.province.dao;

import redis.province.Province;

import java.util.List;

public interface ProvinceDao {
    List<Province> findAll();
}
