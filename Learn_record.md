## Mybatis3
 
> 根据MyBatis 的配置规范配置好后，通过SqlSession.getMapper(XXXMapper.class) 方法，
MyBatis 会根据相应的接口声明的方法信息，通过动态代理机制生成一个Mapper 实例，
我们使用Mapper 接口的某一个方法时，MyBatis 会根据这个方法的方法名和参数类型，确定Statement Id，底层还是通过SqlSession.select("statementId",parameterObject);或者SqlSession.update("statementId",parameterObject); 等等来实现对数据库的操作

- MyBatis 会根据相应的接口声明的方法信息，通过动态代理机制生成一个Mapper 实例,怎么实现动态代理生成实例的？自己用代码实现动态代理

> MyBatis 通过传入的参数值，使用 Ognl 来动态地构造SQL语句，使得MyBatis 有很强的灵活性和扩展性

- 什么是 Ognl？怎么用？原理是什么？

- Connection 表示与数据库的连接（会话），这里的会话具体是什么含义？与 web 中用户会话有什么本质区别？

### 《深入理解mybatis原理》 Mybatis数据源与连接池

> 20210128

- 什么是 jndi?

- 为什么 PooledDataSource 持有 UnpooledDataSource 引用？底层都是 UnpooledDataSource 实现的？

- MyBatis是通过工厂模式来创建数据源DataSource对象===> 动手实现工厂模式，模仿 mybatis 的代码实现

- 复习并实践设计模式之工厂模式[done]

### 20210301

> 使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能。

- mybatis 怎么使用 OGNL从sql参数对象中计算表达式的值？自己实现OGNL从sql参数对象中计算表达式的值代码。

- 如何编写一个插件？代码实现[done]

- 复习并实践设计模式之责任链模式