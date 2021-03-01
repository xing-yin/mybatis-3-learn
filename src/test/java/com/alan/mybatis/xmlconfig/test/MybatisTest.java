package com.alan.mybatis.xmlconfig.test;

import com.alan.mybatis.xmlconfig.entity.UserInfo;
import com.alan.mybatis.xmlconfig.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2021/1/25
 */

public class MybatisTest {

  /**
   * SqlSessionFactoryBuilder 对象主要用于读取配置文件，解析配置文件和 mapper 文件的每个节点，然后创建 SqlSessionFactory 对象。
   * SqlSessionFactory 是一个工厂类，主要用于创建 SqlSession 对象。
   * UserInfoMapper 对象是个代理类对象，通过 sqlSession 获得。然后就可以通过此代理对象执行 mybatis 流程查询 sql。
   */

  public static void main(String[] args) throws IOException {
    // mybatis加载
    init();
  }

  private static void init() throws IOException {
    SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
    // 创建一个默认DefaultSession
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 通过JDK的动态代理创建一个代理类。
    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
    List<UserInfo> userInfoList = userInfoMapper.getUserInfoByLessDate(LocalDate.now());
    System.out.println(userInfoList);
  }

  /**
   * 获取SqlSessionFactory
   *
   * @return
   */
  private static SqlSessionFactory createSqlSessionFactory() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

}
