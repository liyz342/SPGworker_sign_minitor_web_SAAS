package com.wsminitor.hisexampleserver.mapper;

import com.wsminitor.hisexampleserver.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
MyBatis框架通过XML文件来配置SQL映射，这些XML文件被称为Mapper文件。在你的项目中，`CCashierMapper.xml`就是这样一个文件，它包含了与`CCashierMapper`接口中定义的方法对应的SQL语句。

MyBatis识别和解析Mapper文件的步骤通常如下：

1. **配置阶段**：在MyBatis的配置文件（通常是`mybatis-config.xml`）中，你需要指定Mapper文件的位置。这可以通过`<mappers>`标签完成，你可以使用`<mapper>`标签指定每个Mapper文件的路径，或者使用`<package>`标签指定包含所有Mapper接口的包名，MyBatis会自动扫描该包下的所有接口并找到对应的XML文件。

2. **初始化**：当应用程序启动时，MyBatis会读取配置文件和Mapper文件，并将它们加载到内部的Configuration对象中。这个过程包括解析Mapper文件中的SQL语句和映射信息。

3. **映射注册**：MyBatis会根据Mapper文件中的`namespace`属性来识别对应的Mapper接口。`namespace`的值通常是Mapper接口的完全限定名。例如，`CCashierMapper.xml`中的`<mapper namespace="com.wsminitor.hisexampleserver.mapper.CCashierMapper">`告诉MyBatis这个文件对应`com.wsminitor.hisexampleserver.mapper.CCashierMapper`接口。

4. **执行查询/更新**：当MyBatis执行一个映射器方法时，它会查找与该方法签名匹配的`<select>`、`<insert>`、`<update>`或`<delete>`标签。MyBatis会根据这些标签中的`id`属性来找到正确的SQL语句。例如，`<select id="sel" resultType="ReportVo">`定义了一个名为`sel`的查询，它返回`ReportVo`类型的结果集。

5. **动态SQL**：MyBatis支持动态SQL，允许你根据条件构建复杂的SQL语句。在你的Mapper文件中，`<if>`标签被用来实现模糊查询和条件查询。

6. **参数和结果映射**：`parameterType`属性指定了传入SQL语句的参数类型，而`resultType`属性指定了查询结果应该映射到的Java类型。

通过这种方式，MyBatis将Mapper接口的方法映射到具体的SQL语句，使得开发者可以通过简单的方法调用来执行数据库操作，而不需要编写繁琐的JDBC代码。这种半自动化的数据访问方式简化了数据库编程，并提高了开发效率。
* */
@Mapper
public interface CCashierMapper {
    //查询挂号的所有信息用于页面表格展示
    List<ReportVo> sel();
    //查询所有药房
    List<CWarehuose> selware();
    //根据药房查询所有药品信息在药品详情框中展示
    List<CPharmacy> selpharm(CPharmacy cPharmacy);
    //查询这个用户的处方表上有没有这个药
    Integer selcadr(CCashier cCashier);
    //添加处方药品
    Integer addchu(CCashier cCashier);
    //如果有该药品则改变该药品的数量
    Integer updchu(CCashier cCashier);
    //查询该用户的处方
    List<CCashier> selpepi(Integer perid);
    //删除处方中的药品
    Integer del(CCashier cCashier);
    //减少药房中的数量
    Integer deldrunum(CPharmacy cPharmacy);
    //添加药品数量
    Integer adddrunum(CPharmacy cPharmacy);
    //模糊查询
    List<ReportVo> mohu(ReportVo reportVo);
    //修改用户的病例
    Integer addbing(CReport cReport);
    //查询用户有没有病例
    String selbing(Integer rid);
    //查询用户做项目的结果
    String lookbing(Integer reid);
    //查询该用户是否还有未交钱的项目
    int seljiao(Integer reid);
    //查看该用户有几个已经做过的项目
    Integer selyi(Integer reid);
    //查询该用户有几个已经交过钱的项目
    Integer selgong(Integer reid);
    //查询用户所有的处方
    List<CCashier> selall(Integer perid);
    //查询用户项目的处方
    List<CCashier> selximu(Integer perid);
}
