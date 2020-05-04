# 航空事故服务器

1. src/main/java 放Java代码
2. src/main/resources 放配置文件，html，css，js
2. java->controller ： 控制器，写接口用的
   java->doamin: 实体类，与数据库表关联
   java->mapper: 操作数据库实体类的
   java->service: 负责调用mapper完成业务的。
   java->pojo: ViewObject 业务实体类。
   java->common： 通用工具类
   java->config: 项目的配置类