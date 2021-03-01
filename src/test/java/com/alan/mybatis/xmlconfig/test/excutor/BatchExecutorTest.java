package com.alan.mybatis.xmlconfig.test.excutor;

import com.alan.mybatis.xmlconfig.entity.UserInfo;
import com.alan.mybatis.xmlconfig.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * mybatis 批量操作测试
 *
 * @author Alan Yin
 * @date 2021/2/26
 */

public class BatchExecutorTest {

  public static void main(String[] args) throws IOException {
    init();
  }

  private static void init() throws IOException {
    SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
    // 使用批量操作的 ExecutorType
    SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
    //批量保存执行
    long start1 = System.currentTimeMillis();
    addUsers(userInfoMapper);
    sqlSession.commit();
    System.out.println("批量操作耗时:" + (System.currentTimeMillis() - start1));// 批量操作耗时:6474

    // 单条保存执行
    SqlSession sqlSession2 = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
    UserInfoMapper userInfoMapper2 = sqlSession2.getMapper(UserInfoMapper.class);
    long start2 = System.currentTimeMillis();
    addUsers(userInfoMapper2);
    System.out.println("非批量操作耗时:" + (System.currentTimeMillis() - start2));// 非批量操作耗时:13531

  }

  private static void addUsers(UserInfoMapper userInfoMapper) {
    for (int i = 0; i < 100000; i++) {
      UserInfo userInfo = new UserInfo("Jack" + i, LocalDate.now());
      userInfoMapper.addUser(userInfo);
    }
  }

  private static SqlSessionFactory createSqlSessionFactory() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
  }
}
