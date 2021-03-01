package com.alan.mybatis.xmlconfig.test.pool;

import java.sql.*;

/**
 * @author Alan Yin
 * @date 2021/1/29
 */

public class ConnectionCostTest {

  public static void main(String[] args) throws SQLException {
    long time1 = System.currentTimeMillis();
    // 使用数据库驱动创建连接 Connection 会话
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_test?user=root&password=123456");
    long time2 = System.currentTimeMillis();
    System.out.println("connection cost time: " + (time2 - time1) + " ms");

    PreparedStatement preparedStatement = connection.prepareStatement("select * from user_info limit 1;");
    preparedStatement.executeQuery();
    System.out.println("execute sql cost time: " + (System.currentTimeMillis() - time2) + " ms");
  }

  /// todo 写程序对比池化与非池化性能

}
