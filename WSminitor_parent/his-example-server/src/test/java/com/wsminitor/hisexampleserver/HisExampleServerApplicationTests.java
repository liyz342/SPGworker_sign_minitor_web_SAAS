package com.wsminitor.hisexampleserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class HisExampleServerApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        // 确保Spring应用程序上下文被正确加载
        assertNotNull(dataSource, "Data source should be initialized and not null.");
    }

    @Test
    public void testDatabaseConnection() {
        // 验证数据源是否有效
        try {
            // 尝试打开并关闭数据库连接
            dataSource.getConnection().close();
        } catch (Exception e) {
            // 如果捕获到异常，则认为数据库连接失败，并抛出AssertionError
            throw new AssertionError("Database connection test failed", e);
        }
    }
}