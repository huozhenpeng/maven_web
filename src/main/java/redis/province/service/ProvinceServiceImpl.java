package redis.province.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.province.Province;
import redis.province.dao.ProvinceDao;
import redis.province.dao.ProvinceDaoImpl;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao provinceDao = new ProvinceDaoImpl();
    @Override
    public String findAllJson() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String provinceJson = jedis.get("province");
        if (provinceJson == null || provinceJson.length() == 0) {
            System.out.println("redis没有缓存..........");
            List<Province> provinces = provinceDao.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                provinceJson = objectMapper.writeValueAsString(provinces);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province", provinceJson);
            jedis.close();
            return provinceJson;
        }
        System.out.println("redis有缓存");
        return provinceJson;
    }
}
