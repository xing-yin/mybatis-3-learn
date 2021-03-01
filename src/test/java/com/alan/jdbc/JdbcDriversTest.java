package com.alan.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 通过 jdbc.drivers 加载驱动driver
 *
 * @author Alan Yin
 * @date 2021/1/27
 */

public class JdbcDriversTest {

  public static void main(String[] args) throws SQLException {
    String url = "jdbc:mysql://localhost:3306/";
    // 1.设置系统变量 jdbc.drivers
    System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
    // 2.通过指定 url 获取 driver
    Driver driver = DriverManager.getDriver(url);
    System.out.println("success:" + driver);
  }
}
