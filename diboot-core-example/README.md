## diboot-core 示例项目

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
http://localhost:8080/example/department/listWithDTO

> Controller继承自BaseCrudMappingRestController则自动拥有CRUD mapping接口，
如果不需要继承mapping接口，可继承自BaseCrudRestController/BaseController。

> 注解自动绑定依赖实体对应的BaseService类，需确保你的{Entity}Service,{Entity}ServiceImpl,{Entity}Mapper存在。
具体可参照本示例中。
