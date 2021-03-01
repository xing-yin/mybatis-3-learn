package com.alan.jdbc;

import java.sql.*;
import java.util.Properties;

/**
 * @author Alan Yin
 * @date 2021/1/27
 */

public class DBConnectionTest {

  static final String URL = "jdbc:mysql://localhost:3306/mybatis_test";
  static final String USER_NAME = "root";
  static final String PASSWORD = "123456";

  public static void main(String[] args) {
    Statement statement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    try {
      //1.加载类，并注册驱动器（Driver 会注册到 DriverManager 中）
      //加载 mysql 数据库驱动
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

      //2.根据特定的URL，返回可以接受此URL的数据库驱动对象
      Driver driver = DriverManager.getDriver(URL);
      Properties props = new Properties();
      props.put("user", USER_NAME);
      props.put("password", PASSWORD);

      //3.使用数据库驱动创建数据库连接Connection会话
      connection = driver.connect(URL, props);

      //4.获得Statement对象
      statement = connection.createStatement();

      //5.执行 sql语句，返回结果
      resultSet = statement.executeQuery("select * from mybatis_test");

      //6.处理结果，取出数据
      while (resultSet.next()) {
        System.out.println(resultSet.getString(2));
      }
    } catch (Exception e) {
      System.out.println("加载 mysql 类失败！");
      e.printStackTrace();
    } finally {
      //7.关闭链接，释放资源
      // 使用完成后管理链接，释放资源，释放顺序应该是： ResultSet ->Statement ->Connection
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
