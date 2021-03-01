package com.alan.mybatis.xmlconfig.test.cache;

import com.alan.mybatis.xmlconfig.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 二级缓存：
 *
 * @author Alan Yin
 * @date 2021/2/12
 */

public class L2CacheTest {
  public static void main(String[] args) throws IOException {
    SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 3.使用SqlSession查询: 通过JDK的动态代理创建一个代理类。
    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
    long beginTime = System.currentTimeMillis();
    userInfoMapper.getAllUserInfo();
    long noCacheTime = System.currentTimeMillis();
    System.out.println("no cache cost time:" + (noCacheTime - beginTime));
    userInfoMapper.getAllUserInfo();
    System.out.println("cache cost time:" + (System.currentTimeMillis() - noCacheTime));
  }

  private static SqlSessionFactory createSqlSessionFactory() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
  }
}
