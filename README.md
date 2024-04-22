# 工人疲劳检测系统 Web 端应用
本项目为工人疲劳检测系统的web端应用，应用动态数据库切换技术实现对不同机构提供SAAS模板服务。服务包括但不限于：
- 工人数据查看
- 疲劳预警
- 数据上报
  左侧工具栏可用于拓展其他服务。

## 数据库预处理
1. **新建数据库**
    - 名称：`his`
    - 编码格式：选择 `utf8mb4`

2. **导入数据库文件**
    - 路径：`/WSminitor_parent/his-example-server/src/main/resources/his.sql`

3. **创建新数据库模板**
    - 每次引入新的机构时，都会按照模板创建新的数据库。
    - 模板路径：`/WSminitor_parent/his-example-server/src/main/resources/hisTemplate.sql`

## 启动项目
1. 在IDEA中选择 `HisExampleServerApplication` 这个 service。
2. 点击 `run` 按钮。
3. 打开浏览器，访问 `http://localhost:8080` 查看平台。
4. 使用以下凭据登录：
    - 用户名：`admin`
    - 密码：`123456`