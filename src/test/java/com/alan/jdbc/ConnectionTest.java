package com.alan.jdbc;

import java.sql.*;

/**
 * @author Alan Yin
 * @date 2021/1/27
 */

public class ConnectionTest {

  public static void main(String[] args) throws Exception {
//    connectionStatementTest();

//    connectionTransactionTest();
    connectionDatabaseMetaDataTest();
  }


  public static void connectionStatementTest() throws Exception {
    // 使用数据库驱动创建连接 Connection 会话
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=123456");

    // 演示1：创建静态的 sql 语句，Statement 对象将 sql 发送到数据库
    Statement statement = connection.createStatement();
    // 演示2：创建 CallableStatement 对象调用数据库存储过程
    CallableStatement callableStatement = connection.prepareCall("select * from test_table;");
    // 演示3：创建参数化的 PreparedStatement 对象
    PreparedStatement preparedStatement = connection.prepareStatement("select * from test_table;");
  }

  public static void connectionTransactionTest() throws SQLException {
    Connection connection = null;
    try {
      // 使用数据库驱动创建连接 Connection 会话
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_test?user=root&password=123456");
      // 使用自定义事务，需要先设置 connection 不自动提交（默认自动提交）
      connection.setAutoCommit(false);

      String sql = "insert into user_info(name,deleteFlag) values('yin','false')";
      Statement staticStatement = connection.createStatement();
      staticStatement.execute(sql);
      connection.commit();
    } catch (SQLException throwables) {
      // 若发生异常，则 rollback
      connection.rollback();
    }
  }

  public static void connectionDatabaseMetaDataTest() throws SQLException {
    // 使用数据库驱动创建连接 Connection 会话
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_test?user=root&password=123456");
    DatabaseMetaData databaseMetaData = connection.getMetaData();
    System.out.println(databaseMetaData);
  }


}
