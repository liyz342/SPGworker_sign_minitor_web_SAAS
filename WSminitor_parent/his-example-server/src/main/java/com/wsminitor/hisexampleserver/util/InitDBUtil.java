package com.wsminitor.hisexampleserver.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
* `InitDBUtil`是一个工具类，它提供了一系列静态方法来处理数据库初始化和连接相关的任务。这个类主要用于在SaaS应用中为医院创建新的数据库实例，以及验证数据库连接。以下是`InitDBUtil`类中定义的主要方法及其功能：

1. `initDB`：这是一个静态方法，用于初始化数据库。它接受MySQL的URL、数据库schema名称、用户名和密码作为参数。方法内部首先建立与MySQL的连接，然后执行删除（如果存在）、创建和使用指定schema的SQL语句。接着，使用MyBatis的`ScriptRunner`来执行一个SQL脚本（`hisTemplate.sql`），这个脚本通常包含了创建表、插入初始数据等操作。如果整个过程成功执行，方法返回`true`；如果发生异常，记录错误日志并返回`false`。

2. `tryConnectDB`：这个方法尝试连接到指定的数据库URL，如果连接成功则返回`true`，否则记录错误日志并返回`false`。这个方法主要用于验证提供的数据库连接信息是否正确。

3. `getInitDBConfig`：这个方法创建并配置一个`DruidDataSource`实例，这是阿里巴巴提供的数据库连接池实现。它设置了一些连接池的参数，如初始大小、最小空闲连接数、最大活跃连接数等，并提供了用于检测连接有效性的SQL语句。这个配置好的`DruidDataSource`实例可以用于MyBatis Plus或其他数据库访问框架。

`InitDBUtil`类使用了`Logger`来记录日志，这有助于跟踪和调试数据库操作过程中的问题。此外，它使用了模板字符串来构建SQL语句和数据库连接URL，这使得代码更加灵活和可配置。

总的来说，`InitDBUtil`类为应用程序提供了一套方便的工具，用于管理和初始化医院的数据库实例，确保SaaS应用能够为每个医院提供独立的数据存储空间。
* */
public class InitDBUtil {

    private static Logger logger= LoggerFactory.getLogger(InitDBUtil.class);
    private static final String jdbcurlTemplate = "jdbc:mysql://#{mysqlUrl}/mysql?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";

    private static final String dropSchemaSqlTemplate = "DROP DATABASE IF EXISTS #{schema}";
    private static final String createSchemaSqlTemplate = "CREATE DATABASE `#{schema}` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'; ";
    private static final String useSchemaSqlTemplate = "use `#{schema}`;";

    public static boolean initDB(String mysqlUrl,String schema,String username,String password){
        Connection connection = null;
        try{
            Class.forName(driverClass);
            connection = DriverManager.getConnection(jdbcurlTemplate.replace("#{mysqlUrl}",mysqlUrl), username, password);
            Statement statement = connection.createStatement();
            statement.execute(dropSchemaSqlTemplate.replace("#{schema}",schema));
            statement.execute(createSchemaSqlTemplate.replace("#{schema}",schema));
            statement.execute(useSchemaSqlTemplate.replace("#{schema}",schema));

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setStopOnError(true);

            ClassPathResource classPathResource = new ClassPathResource("hisTemplate.sql");
            InputStream inputStream = classPathResource.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            scriptRunner.runScript(isr);
            return true;
        }catch(Exception e){
            logger.error("初始化数据库失败;",e.getMessage());
            return false;
        }finally {
            if(null != connection){
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    public static boolean tryConnectDB(String mysqlUrl,String schema,String username,String password){
        Connection connection = null;
        try{
            Class.forName(driverClass);
            connection = DriverManager.getConnection(jdbcurlTemplate.replace("#{mysqlUrl}",mysqlUrl), username, password);
            return true;
        }catch(Exception e){
            logger.error("连接化数据库失败;",e.getMessage());
            return false;
        }finally {
            if(null != connection){
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    public static DruidDataSource getInitDBConfig(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }
}
