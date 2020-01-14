## diboot-core 示例项目

[diboot-core](https://github.com/dibo-software/diboot-v2/tree/master/diboot-core) 精简内核主要实现了：
1. 单表CRUD无SQL
   > 基于Mybatis-Plus实现（Mybatis-Plus具备通用Mapper方案和灵活的查询构造器）
2. 关联查询无SQL（注解自动绑定）
   > 扩展实现了多表关联查询的无SQL方案，只需要一个简单注解@Bind*，就可以实现关联对象（含字段、实体、实体集合等）的数据绑定，且实现方案是将关联查询拆解为单表查询，保障最佳性能。
3. 数据字典无SQL（注解自动绑定）
   > 通过@BindDict注解实现数据字典(枚举)的存储值value与显示值name的转换。
4. Entity/DTO自动转换为QueryWrapper
   > @BindQuery注解绑定字段参数对应的查询条件类型，Controller中直接绑定转换为QueryWrapper，无需再手动构建QueryWrapper查询条件
5. 其他常用工具类的最佳实践封装
   > 字符串处理S、常用校验V、日期D、扩展的BeanUtils等
   
本示例展示基于diboot-core的单表CRUD和多表关联的无SQL、Entity/DTO自动转换为QueryWrapper等的使用样例。

### example运行步骤：

#### 0. 将该代码clone到你本地IDE

#### 1. 将application.properties里的如下数据库连接参数改为你的配置
>   spring.datasource.url

>   spring.datasource.username

>   spring.datasource.password

#### 2. 执行example相关SQL：
打开[MySQL初始化脚本](https://github.com/dibo-software/diboot-v2-example/blob/master/diboot-core-example/src/main/resources/META-INF/sql/init-mysql.sql)
并在你的MySQL客户端执行（暂只提供MySQL样例，其他数据库请自行转换）。

#### 3. 启动diboot-core-example项目，让starter自动安装directory表。
如安装失败，请打开diboot-core-*.jar/META-INF/sql中对应数据库的脚本手动执行。

#### 4. 编译运行该项目
浏览器访问如下接口，你会看到接口返回json已经自动绑定了VO中声明的关联（关联字段、实体、实体集合、数据字典）
http://localhost:8080/example/user/list
http://localhost:8080/example/department/list

> Controller继承自BaseCrudMappingRestController则自动拥有CRUD mapping接口，
如果不需要继承mapping接口，可继承自BaseCrudRestController/BaseController。

> 注解自动绑定依赖实体对应的BaseService类，需确保你的{Entity}Service,{Entity}ServiceImpl,{Entity}Mapper存在。
具体可参照本示例中。
