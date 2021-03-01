package com.alan.mybatis.xmlconfig.test.pool;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 池化数据源测试
 *
 * @author Alan Yin
 * @date 2021/1/29
 */

public class PooledDataSourceTest {

  public static void main(String[] args) throws SQLException {
    long time1 = System.currentTimeMillis();
    PooledDataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver",
      "jdbc:mysql://localhost:3306/mybatis_test",
      "root",
      "123456");
    Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select * from user_info limit 1;");
    preparedStatement.executeQuery();
    connection.close();
    System.out.println("PooledDataSource cost time:" + (System.currentTimeMillis() - time1));
  }

}
