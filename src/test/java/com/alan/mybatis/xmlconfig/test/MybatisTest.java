package com.alan.mybatis.xmlconfig.test;

import com.alan.mybatis.xmlconfig.entity.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
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
    // 2.创建一个默认DefaultSession: 从SqlSession工厂 SqlSessionFactory中创建一个SqlSession，进行数据库操作
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 3.使用SqlSession查询: 通过JDK的动态代理创建一个代理类。
//    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
//    List<UserInfo> userInfoList1 = userInfoMapper.getAllUserInfo();
//    System.out.println(userInfoList1);

    // 直接使用 SqlSession 查询
    List<UserInfo> userInfoList2 = sqlSession.selectList("com.alan.mybatis.xmlconfig.mapper.UserInfoMapper.getAllUserInfo");
    System.out.println(userInfoList2);

//    Map<String, Object> params = new HashMap<String, Object>();
//    params.put("id", 1);
//    params.put("name", "yin");
//    sqlSession.select("com.alan.mybatis.xmlconfig.mapper.UserInfoMapper.getSingleUserInfo", params, null);

  }

  /**
   * 获取SqlSessionFactory:
   * 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
   * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用
   *
   * @return
   */
  private static SqlSessionFactory createSqlSessionFactory() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

}
