## diboot-core 示例项目

[diboot-core](https://github.com/dibo-software/diboot-v2/tree/master/diboot-core) 组件主要实现了：
1. 多表关联的自动绑定, 实现单表CRUD和多表关联的无SQL化注解绑定
2. 提供其他常用开发场景的最佳实践封装。

本示例展示基于diboot-core的单表CRUD和多表关联的无SQL的使用样例。

### example运行步骤：

#### 0. 将该代码clone到你本地IDE

#### 1. application.properties里配置diboot.core.init-sql=true，以便让starter自动安装directory表
> diboot.core.init-sql=true

#### 2. 将application.properties里的如下数据库连接参数改为你的配置
>   spring.datasource.url

>   spring.datasource.username

>   spring.datasource.password

#### 3. 启动diboot-core-example项目，让starter自动安装directory表。
如果打印SQL执行成功信息后将diboot.core.init-sql配置项删除。
如安装失败，请打开diboot-core-*.jar/META-INF/sql中对应数据库的脚本手动执行。

#### 3. 编译运行该项目
浏览器访问如下接口，你会看到接口返回json已经自动绑定了VO中声明的关联（关联字段、实体、实体集合、数据字典）
http://localhost:8080/example/user/list
http://localhost:8080/example/department/list
> 注解自动绑定依赖实体对应的IService类，需确保你的{Entity}Service,{Entity}ServiceImpl,{Entity}Mapper存在。
具体可参照本示例中。
