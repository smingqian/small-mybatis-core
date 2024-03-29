package edu.nwpu.mybatis.test.dao;

import edu.nwpu.mybatis.binding.MapperProxyFactory;
import edu.nwpu.mybatis.binding.MapperRegistry;
import edu.nwpu.mybatis.session.SqlSession;
import edu.nwpu.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("edu.nwpu.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("edu.nwpu.mybatis.test.dao.IUserDao", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
/*        IUserDao iUserDao = factory.newInstance(sqlSession);
        System.out.println(iUserDao);
        System.out.println(iUserDao.queryUserName("1"));*/
    }
    @Test
    public void test_MapperScanner() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("edu.nwpu.mybatis.test.dao");

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserName("sunq");
        logger.info("result:{}",res);

    }
}
