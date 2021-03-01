package com.alan.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alan Yin
 * @date 2021/1/26
 */

public class DriverManagerTest {

  public static void main(String[] args) {
//    driverManagerTest();

//    deregisterDriverTest();
    deregisterDriverTest2();
  }

  /**
   * 使用DriverManager获取指定Driver
   */
  public static void driverManagerTest() {
    try {
      Driver driver = DriverManager.getDriver("jdbc:mysql://localhost:3306/?user=root&password=123456");
      System.out.println(driver);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  /**
   * 使用 DriverManager 注册和取消注册驱动 Driver
   */
  public static void deregisterDriverTest() {
    try {
      // 1.将Driver加载到内存中，然后执行其 static 静态代码，创建一个 MysqlDriver实例注册到 DriverManager 中
      String url = "jdbc:mysql://localhost:3306/?user=root&password=123456";
      // 取出对应的 mysql 驱动 driver
      Driver driver = DriverManager.getDriver(url);
      System.out.println("success:" + driver);

      // todo 将 driver 从 DriverManager 中注释掉
      DriverManager.deregisterDriver(driver);
      // 重新通过 url 从 DriverManager 中取 Driver
      driver = DriverManager.getDriver(url);
    } catch (SQLException throwables) {
      System.out.println("加载 mysql 驱动类失败！驱动 Driver 已经被取消注册");
      throwables.printStackTrace();
    }
  }

  /**
   * 先创建了一个Driver对象，在注销了DriverManager中由加载驱动过程中静态创建驱动之后，注册到系统中，
   * 现在DriverManager中对应url返回的Driver, 即是在代码中创建的Driver对象。
   */
  public static void deregisterDriverTest2() {
    try {
      String url = "jdbc:mysql://localhost:3306/?user=root&password=123456";
      // 1.将Driver加载到内存中，然后执行其 static 静态代码，创建一个 MysqlDriver 实例注册到 DriverManager 中
      Driver driver0 = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      // 2.取出对应的 mysql 驱动 driver
      Driver driver = DriverManager.getDriver(url);
      System.out.println("success:" + driver);

      // 3.将 driver 从 DriverManager 中注释掉
      DriverManager.deregisterDriver(driver);

      // todo 4.此时DriverManager中已经没有了驱动Driver实例，将创建的dd注册到DriverManager中
      DriverManager.registerDriver(driver0);

      // 5.重新通过 url 从 DriverManager 中取 Driver
      driver = DriverManager.getDriver(url);
      System.out.println("注销掉静态创建的Driver后，重新注册的Driver:" + driver);
      System.out.println("driver和dd是否是同一对象：" + (driver == driver0));
    } catch (SQLException throwables) {
      System.out.println("加载 mysql 驱动类失败！驱动 Driver 已经被取消注册");
      throwables.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}
