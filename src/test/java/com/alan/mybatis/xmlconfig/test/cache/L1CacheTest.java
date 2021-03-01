package com.alan.mybatis.xmlconfig.test.cache;

import com.alan.mybatis.xmlconfig.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一级缓存(mybatis 默认)
 *
 * @author Alan Yin
 * @date 2021/2/12
 */

public class L1CacheTest {

  public static void main(String[] args) throws IOException {
    SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 3.使用SqlSession查询: 通过JDK的动态代理创建一个代理类。
    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
//    long beginTime = System.currentTimeMillis();
//    userInfoMapper.getAllUserInfo();
//    long noCacheTime = System.currentTimeMillis();
//    System.out.println("no cache cost time:" + (noCacheTime - beginTime));
//    userInfoMapper.getAllUserInfo();
//    System.out.println("cache cost time:" + (System.currentTimeMillis() - noCacheTime));
//    // 3:增加一些无关的参数，仍然会走缓存
//    long time5 = System.currentTimeMillis();
//    Map<String, Object> params = new HashMap<String, Object>();
//    params.put("id", 1);
//    sqlSession.selectList("com.alan.mybatis.xmlconfig.mapper.UserInfoMapper.getAllUserInfo", params);
//    System.out.println("cache cost time:" + (System.currentTimeMillis() - time5));

    // -----------------------------------------------------
    long time1 = System.currentTimeMillis();
    userInfoMapper.getSingleUserInfo(1L);
    long time2 = System.currentTimeMillis();
    System.out.println("no cache cost time:" + (time2 - time1));
    // 参数不变，使用缓存
    userInfoMapper.getSingleUserInfo(1L);
    long time3 = System.currentTimeMillis();
    System.out.println("cache cost time:" + (time3 - time2));
    // 参数变化，不走缓存
    userInfoMapper.getSingleUserInfo(2L);
    long time4 = System.currentTimeMillis();
    System.out.println("cache cost time:" + (time4 - time3));

  }

  private static SqlSessionFactory createSqlSessionFactory() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

}
