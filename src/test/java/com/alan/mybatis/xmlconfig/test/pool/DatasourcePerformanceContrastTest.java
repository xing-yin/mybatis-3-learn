package com.alan.mybatis.xmlconfig.test.pool;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不同数据源性能对比
 *
 * @author Alan Yin
 * @date 2021/1/29
 */

public class DatasourcePerformanceContrastTest {

  public static void main(String[] args) throws SQLException {
    // UnpooledDataSource 性能测试: PooledDataSource cost time:11662 ms
    dataSourceTest("UnpooledDataSource");
    // PooledDataSource 性能测试: PooledDataSource cost time:1709 ms
    dataSourceTest("PooledDataSource");

//    pooledDataSourcePopConnectionTest("PooledDataSource");
  }

  private static void dataSourceTest(String type) throws SQLException {
    DataSource dataSource;
    long time1 = System.currentTimeMillis();
    if ("UnpooledDataSource".equals(type)) {
      dataSource = new UnpooledDataSource("com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost:3306/mybatis_test",
        "root",
        "123456");
    } else {
      dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost:3306/mybatis_test",
        "root",
        "123456");
    }
    for (int i = 0; i < 10000; i++) {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user_info limit 1;");
        preparedStatement.executeQuery();
      } finally {
        connection.close();
      }
    }
    System.out.println(type + " cost time:" + (System.currentTimeMillis() - time1) + " ms");
  }

  /**
   * 测试池化数据源获取连接的内部实现
   * @param type
   * @throws SQLException
   */
  private static void pooledDataSourcePopConnectionTest(String type) throws SQLException {
    DataSource dataSource;
    long time1 = System.currentTimeMillis();
    if ("UnpooledDataSource".equals(type)) {
      dataSource = new UnpooledDataSource("com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost:3306/mybatis_test",
        "root",
        "123456");
    } else {
      dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost:3306/mybatis_test",
        "root",
        "123456");
    }

    ExecutorService es = Executors.newFixedThreadPool(20);
    for (int i = 0; i < 10000; i++) {
      es.execute(() -> {
        Connection connection = null;
        try {
          connection = dataSource.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("select sleep(1) as t;");
          preparedStatement.executeQuery();
        } catch (Exception e) {
          // ignore
        } finally {
          try {
            connection.close();
          } catch (SQLException throwables) {
            throwables.printStackTrace();
          }
        }
      });
    }
    System.out.println(type + " cost time:" + (System.currentTimeMillis() - time1) + " ms");
  }

}
