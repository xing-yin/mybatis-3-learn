package com.alan.mybatis.xmlconfig.test.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义一个TypeHandler，实现TypeHandler的setParameter()和getResult()接口方法
 * <p>
 * TypeHandler有两个作用，一是完成从javaType至jdbcType的转换，
 * 二是完成jdbcType至javaType的转换，体现为setParameter()和getResult()两个方法，分别代表设置sql问号占位符参数和获取列查询结果。
 *
 * @author Alan Yin
 * @date 2021/3/1
 */

public class CustomerTypeHandler implements TypeHandler<Object> {

  @Override
  public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

  }

  @Override
  public Object getResult(ResultSet rs, String columnName) throws SQLException {
    return null;
  }

  @Override
  public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
    return null;
  }

  @Override
  public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return null;
  }
}
