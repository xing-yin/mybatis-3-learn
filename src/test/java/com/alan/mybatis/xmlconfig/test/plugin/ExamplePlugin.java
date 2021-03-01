package com.alan.mybatis.xmlconfig.test.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 自定义插件
 * <p>
 * 第一步:新建类实现 Interceptor 接口，并指定想要拦截的方法签名
 * 第二步:MyBatis 配置文件中添加该插件
 *
 * @author Alan Yin
 * @date 2021/3/1
 */
//注意看这个大花括号，也就这说这里可以定义多个@Signature对多个地方拦截，都用这个拦截器
@Intercepts({@Signature(
  type = Executor.class,// 指定拦截哪个接口
  method = "query",// 指定接口内的方法名
  //拦截方法的入参，按顺序写到这，不要多也不要少，如果方法重载，是要通过方法名和入参来确定唯一的
  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExamplePlugin implements Interceptor {

  // 每次执行操作时，都会进入这个拦截器的方法内
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    // 自定义插件的核心逻辑
    for (Object arg : invocation.getArgs()) {
      System.out.println("arg 参数为：" + arg);
    }
    System.out.println("method 方法为：" + invocation.getMethod());
    System.out.println("target 目标对象为：" + invocation.getTarget());

    Object result = invocation.proceed();
    if (result instanceof List) {
      // 此处为了演示，只获取第一个数据
      System.out.println("原集合数据大小：" + ((List) result).size());
      System.out.println("只获取第一个对象");
      List list = (List) result;
      return Arrays.asList(list.get(0));
    }
    return result;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
    // NOP
  }
}
