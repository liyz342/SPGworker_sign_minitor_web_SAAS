package com.wsminitor.hisexampleserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.wsminitor.hisexampleserver.entity.HospitalMeta;
import com.wsminitor.hisexampleserver.service.HospitalService;
import com.wsminitor.hisexampleserver.util.InitDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 这段代码是一个Spring Boot应用程序中的配置类，用于动态配置和管理多个数据源。这个类的主要作用是在应用程序启动时加载和配置所有需要连接的数据库，并为每个数据库创建一个`DruidDataSource`实例。以下是对这个类的主要组成部分的解释：

1. **包声明** (`package com.wsminitor.hisexampleserver.config;`):
   - 定义了类的包路径，这是Java中组织类的一种方式，有助于避免类名冲突。

2. **导入的依赖**:
   - 导入了需要的类和接口，例如日志记录器`Logger`、数据源`DataSource`、实体类`HospitalMeta`、服务类`HospitalService`等。

3. **类定义** (`public class DynamicDatasourceConfig`):
   - 这是一个组件类，使用`@Component`注解，这意味着Spring容器会在启动时创建这个类的实例，并将其作为Bean进行管理。

4. **成员变量**:
   - `hospitalTable`: 一个映射，用于存储数据库schema和医院名称之间的关系。
   - `hospitalService`: 医院服务的Spring Bean，用于获取医院的元数据。
   - `dataSource`: 动态路由数据源的Spring Bean，用于管理多个数据源。

5. **`loadAllDB`方法** (`@PostConstruct`):
   - 这个方法在Spring容器创建Bean后立即执行，用于初始化所有的数据库连接。
   - 它首先设置一个默认的数据源（"master"），然后查询所有可用的医院元数据。
   - 对于每个医院，它创建一个新的`DruidDataSource`实例，并将其添加到动态路由数据源中。

6. **`addDB`方法**:
   - 这个方法用于动态添加一个新的数据库连接。它创建一个新的`DruidDataSource`实例，并将其添加到动态路由数据源中。
   - 这个方法目前被标记为`//懒得做了`，可能是因为开发者暂时没有实现删除缓存的逻辑。

7. **`deleteDB`方法**:
   - 这个方法的注释表明它应该用于删除数据库连接，但目前没有实现。

8. **`getHospitalNameBySchema`方法**:
   - 根据给定的schema，这个方法返回关联的医院名称。如果schema不存在，则返回空字符串。

这个类的设计允许应用程序在运行时动态地管理多个数据库连接，这对于需要连接到多个数据库的复杂应用程序非常有用。例如，在一个多租户的SAAS（Software as a Service）应用程序中，每个租户可能需要自己的数据库，动态数据源配置可以方便地为每个租户创建和管理数据库连接。
* */
@Component
public class DynamicDatasourceConfig {

    private Logger logger = LoggerFactory.getLogger(DynamicDatasourceConfig.class);

    private Map<String, String> hospitalTable = new HashMap<>();

    @Resource
    private HospitalService hospitalService;

    @Resource
    private DynamicRoutingDataSource dataSource;

    @PostConstruct
    public void loadAllDB(){
        hospitalTable.put("master","管理中心");
        List<HospitalMeta> hospitalMetas = hospitalService.selectAvailable(new HospitalMeta());
        for (HospitalMeta hospitalMeta:hospitalMetas){
            DruidDataSource tmpdb = InitDBUtil.getInitDBConfig();
            tmpdb.setUsername(hospitalMeta.getMysqlUsername());
            tmpdb.setPassword(hospitalMeta.getMysqlPassword());
            tmpdb.setUrl("jdbc:mysql://"+hospitalMeta.getMysqlUrl()+"/"+hospitalMeta.getMysqlSchema()+"?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
            dataSource.addDataSource(hospitalMeta.getMysqlSchema(),tmpdb);
            logger.info("======加载动态数据库完成：mysqlSchema="+hospitalMeta.getMysqlSchema());

            hospitalTable.put(hospitalMeta.getMysqlSchema(),hospitalMeta.getHospitalName());
        }
    }
    //初始化医院的mysql库后，动态增加缓存。
    public void addDB(HospitalMeta hospitalMeta){
        DruidDataSource tmpdb = InitDBUtil.getInitDBConfig();
        tmpdb.setUsername(hospitalMeta.getMysqlUsername());
        tmpdb.setPassword(hospitalMeta.getMysqlPassword());
        tmpdb.setUrl("jdbc:mysql://"+hospitalMeta.getMysqlUrl()+"/"+hospitalMeta.getMysqlSchema()+"?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource.addDataSource(hospitalMeta.getMysqlSchema(),tmpdb);
        logger.info("======加载添加数据库完成：mysqlSchema="+hospitalMeta.getMysqlSchema());

        hospitalTable.put(hospitalMeta.getMysqlSchema(),hospitalMeta.getHospitalName());
    }
    //删除缓存--懒得做了。
    public void deleteDB(){

    }

    public String getHospitalNameBySchema(String schema){
        return hospitalTable.containsKey(schema)?hospitalTable.get(schema):"";
    }
}
