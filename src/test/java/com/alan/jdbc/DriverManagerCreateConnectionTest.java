package com.alan.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用DriverManager创建 Connection 连接对象
 *
 * @author Alan Yin
 * @date 2021/1/26
 */

public class DriverManagerCreateConnectionTest {

  public static void main(String[] args) {
    getConnectionTest();
  }

  private static void getConnectionTest() {
    String url = "jdbc:mysql://localhost:3306/?user=root&password=123456";
    try {
      // 方式1【推荐】
      Connection connection1 = DriverManager.getConnection(url);
      System.out.println(connection1);
      // 方式2
      Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      Connection connection2 = driver.connect(url,new Properties());
      System.out.println(connection2);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}
