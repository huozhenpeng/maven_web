#####1、概念：
* mybatis 是一个优秀的基于 java 的持久层框架，它内部封装了 jdbc，使开发者只需要关注 sql 语句本身，
而不需要花费精力去处理加载驱动、创建连接、创建 statement 等繁杂的过程。
mybatis 通过 xml 或注解的方式将要执行的各种 statement 配置起来，并通过 java 对象和 statement 中
sql 的动态参数进行映射生成最终执行的 sql 语句，最后由 mybatis 框架执行 sql 并将结果映射为 java 对象并
返回。
* 采用 ORM 思想解决了实体和数据库映射的问题，对 jdbc 进行了封装，屏蔽了 jdbc api 底层访问细节，使我
们不用与 jdbc api 打交道，就可以完成对数据库的持久化操作。


#####2、延迟加载

就是在需要用到数据时才进行加载，不需要用到数据时就不加载数据。延迟加载也称懒加载.
* 好处：先从单表查询，需要时再从关联表去关联查询，大大提高数据库性能，因为查询单表要比关联查询多张表速
度要快。
* 坏处：因为只有当需要用到数据时，才会进行数据库查询，这样在大批量数据查询时，因为查询工作也要消耗
时间，所以可能造成用户等待时间变长，造成用户体验下降。

>需求

查询账户account信息并且关联查询该账户下的订单信息，订单信息在另一张表中，当需要展示订单
信息时才需要查询第二张表，如果只是展示账户信息，那么就没有必要查询订单表，这就是延迟加载。

* association、collection 具备延迟加载功能


#####3、mybatis缓存
* 一级缓存：一级缓存是 SqlSession 级别的缓存，只要 SqlSession 没有 flush 或 close，它就存在。
* 二级缓存：二级缓存是 mapper 映射级别的缓存，多个 SqlSession 去操作同一个 Mapper 映射的 sql 语句，多个
       SqlSession 可以共用二级缓存，二级缓存是跨 SqlSession 的。
* 一级缓存应该是无法控制是否生效的，二级缓存可以控制


>开启二级缓存
步骤：

第一步：
```
<settings>
<!-- 开启二级缓存的支持 --> <setting name="cacheEnabled" value="true"/>
</settings>
```
因为 cacheEnabled 的取值默认就为 true，所以这一步可以省略不配置。为 true 代表开启二级缓存；为
false 代表不开启二级缓存。

第二步：
配置相关的Mapper映射文件
```
<cache>标签表示当前这个 mapper 映射将使用二级缓存，区分的标准就看 mapper 的 namespace 值。
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper 
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd"> <mapper namespace="com.itheima.dao.IUserDao">
<!-- 开启二级缓存的支持 -->
<cache></cache>
</mapper>
```

第三步：
设置useCache属性
```
<!-- 根据 id 查询 --> 
<select id="findById" resultType="user" parameterType="int" useCache="true">
select * from user where id = #{uid}
</select> 
将映射文件中的<select>标签中设置 useCache=”true”代表当前这个 statement 要使用
二级缓存，如果不使用二级缓存可以设置为 false。
注意：针对每次查询都需要最新的数据 sql，要设置成 useCache=false，禁用二级缓存。
```






