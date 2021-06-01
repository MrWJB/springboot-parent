## 多数据源
所谓多数据源，就是一个JavaEE项目中采用了不同数据库实例中的多个库，或者同一个数据库实例中多个不同
的库。
一般来说，采用MyCat等分布式数据库中间件是比较好的解决方案，这样可以把数据库读写分离、分库分表
、备份等操作交给中间件去做，Java代码只需要专注于业务即可。
不过，这并不意味着无法使用Java代码解决类似的问题，在Spring framework中就可以配置多数据源，
Springboot继承其衣钵，只不过配置方式有所变化。

### jdbcTemplate多数据源
JdbcTemplate多数据源的配置是比较简单的，因为一个JdbcTemplate对应一个DataSource,开发者
只需要手动提供多个DataSource，再手动配置JdbcTemplate即可。

创建数据库chapter05-1、chapter05-2。两个库中都配置book表，再各预设1条数据。
步骤：
  1. 创建数据库
  2. 创建项目
  3. 配置数据库连接
  4. 配置数据源
  5. 配置JdbcTemplate
  6. 创建BookController
  7. 测试
  
  
 ## mybatis多数据源
 1. 准备工作
 2. 创建mybatis配置
 3. 创建mapper
 4. 创建controller
 5. 测试
 
 ## jpa多数据源
 1. 准备工作
 2. 创建实体
 3. 创建JPA配置
 4. 创建Repository
 5. 创建Controller
 6. 测试
 
 jpa配置和mybatis配置多数据源类似，不同的是，jpa配置时主要提供不同的
 LocalContainerEntityManangerFactoryBean 以及事务管理器。
 
 
 总结：
    jdbcTemplate使用的并不是很广泛；
    mybatis灵活性最好，方便开发者进行sql优化；
    spring jpa使用方便，特别是快速实现一个restful风格的应用；