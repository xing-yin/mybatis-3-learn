package com.alan.jdbc;

import java.sql.*;
import java.util.Properties;

/**
 * 例子:手动加载驱动 Driver 并实例化进行数据库操作
 *
 * @author Alan Yin
 * @date 2021/1/26
 */

public class ManualDriverTest {

  public static void main(String[] args) {
    driverTest();
  }

  public static void driverTest() {
    try {
      // 1.加载 mysql 驱动类，并实例化
      Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

      // 2.判指定的 URL mysql 驱动能否接受(符合 mysql 协议规则)
      boolean flag = driver.acceptsURL("jdbc:mysql://127.0.0.1:3306/mybatis_test");
      // 标准协议测试
      boolean standardFlag = driver.acceptsURL("jdbc:mysql://<host>:<port>/<database_name>");
      System.out.println("协议测试:" + flag + "\t" + standardFlag);

      // 3.创建真实的数据连接
      // 连接方式1
      String url = "jdbc:mysql://127.0.0.1:3306/mybatis_test";
      Properties properties = new Properties();
      properties.put("user", "root");
      properties.put("password", "123456");
      Connection connection = driver.connect(url, properties);
      PreparedStatement preparedStatement = connection.prepareStatement("update user_info set name='alan'");
      System.out.println(preparedStatement.executeUpdate());
//      System.out.println(preparedStatement.executeQuery());
      // 连接方式2
      Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=123456");
      System.out.println(connection == connection2);
    } catch (Exception e) {
      System.out.println("加载 mysql 驱动类失败！");
      e.printStackTrace();
    } finally {
    }
  }

}
