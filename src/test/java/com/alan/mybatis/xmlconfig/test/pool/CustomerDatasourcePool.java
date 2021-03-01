package com.alan.mybatis.xmlconfig.test.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 自定义数据源 pool 池：线程安全的简易连接池实现(单例)
 *
 * @author Alan Yin
 * @date 2021/1/29
 */

public class CustomerDatasourcePool {

  private static Vector<Connection> pool = new Vector<>();

  private static int MAX_COUNT = 100;

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/mybatis_test";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "123456";

  static {
    try {
      Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * 返回一个Connection对象:
   * 如果连接池有元素，则pop出第一个元素
   * 如果连接池Pool中没有元素，则创建一个connection对象，再添加到pool中
   *
   * @return
   */
  public static Connection getConnection() {
    Connection connection;
    synchronized (pool) {
      if (pool.size() > 0) {
        connection = pool.get(0);
        pool.remove(0);
      } else {
        connection = createConnection();
        pool.add(connection);
      }
      return connection;
    }
  }

  /**
   * 创建新连接对象
   *
   * @return
   */
  private static Connection createConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return connection;
  }

  /**
   * 释放连接
   *
   * @param connection
   */
  public static void releaseConnection(Connection connection) {
    synchronized (pool) {
      if (pool.size() < MAX_COUNT) {
        pool.add(connection);
      }
    }
  }

  public static void main(String[] args) throws SQLException {
    Connection connection = CustomerDatasourcePool.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select * from user_info limit 1;");
    preparedStatement.executeQuery();
    // 不再使用 close,使用自定义的 releaseConnection
    // connection.close();
    CustomerDatasourcePool.releaseConnection(connection);
  }

}
