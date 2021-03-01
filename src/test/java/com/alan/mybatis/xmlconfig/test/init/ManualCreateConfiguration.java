package com.alan.mybatis.xmlconfig.test.init;

import com.alan.mybatis.xmlconfig.entity.UserInfo;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 手动加载XML配置文件创建Configuration对象完成初始化，创建并使用SqlSessionFactory对象
 *
 * @author Alan Yin
 * @date 2021/2/5
 */

public class ManualCreateConfiguration {

  public static void main(String[] args) throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream, null, null);
    Configuration configuration = xmlConfigBuilder.parse();
    // 使用Configuration对象创建SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    // 使用 mybatis
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<UserInfo> userInfoList2 = sqlSession.selectList("com.alan.mybatis.xmlconfig.mapper.UserInfoMapper.getAll");
  }

}
