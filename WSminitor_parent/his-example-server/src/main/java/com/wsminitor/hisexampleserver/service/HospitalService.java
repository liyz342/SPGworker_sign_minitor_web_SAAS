package com.wsminitor.hisexampleserver.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsminitor.hisexampleserver.entity.HospitalMeta;
import com.wsminitor.hisexampleserver.mapper.HospitalMapper;
import com.wsminitor.hisexampleserver.util.InitDBUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
/*
* `HospitalService`是一个Spring服务层组件，它提供了与医院元数据（`HospitalMeta`实体）相关的业务逻辑操作。这个服务类使用了MyBatis Plus框架的`QueryWrapper`来构建查询条件，并调用`HospitalMapper`接口中的方法来执行数据库操作。`@DS("master")`注解表明这个服务类的所有方法都将使用名为`master`的数据源，这通常是主数据库或默认数据源的名称。

以下是`HospitalService`中定义的一些主要方法及其功能：

1. `selectAll`：根据提供的`HospitalMeta`对象中的条件（医院ID和MySQL URL）查询所有匹配的医院元数据。

2. `selectAvailable`：查询所有状态为可用（`mysqlStatus`为1）的医院元数据，同时可以根据医院ID和MySQL URL进行过滤。

3. `addHospital`：向数据库中添加一个新的医院元数据记录。

4. `updateHospital`：根据医院ID更新数据库中的医院元数据记录。

5. `deleteHospital`：根据医院ID从数据库中删除一个医院元数据记录。

6. `selectBySchema`：根据提供的MySQL schema查询对应的医院元数据。

7. `initDB`：使用`InitDBUtil`工具类初始化数据库，这通常包括创建数据库、设置用户权限等操作。

8. `tryConnectDB`：尝试连接到提供的数据库URL，验证数据库连接是否成功。

服务层的设计模式遵循了单例模式，它通过`@Resource`注解注入`HospitalMapper`，这是一个MyBatis Plus的Mapper接口，用于执行CRUD操作。`HospitalService`中的方法封装了对数据库的操作，使得控制器（如`HostiptalController`）可以调用这些方法来处理来自用户的请求，而不需要直接与数据库交互。

此外，`HospitalService`中使用了`StringUtils.hasText`来检查字符串是否不为空且长度大于0，这是一种常见的避免空指针异常的做法。`@DS("master")`注解的使用确保了在执行数据库操作时，服务层会使用正确的数据源，这在多数据源的SaaS应用中非常重要。
* */
@Service
@DS("master")
public class HospitalService {

    @Resource
    private HospitalMapper hospitalMapper;

    public List<HospitalMeta> selectAll(HospitalMeta hospital) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null != hospital.getHospitalId() && hospital.getHospitalId()>0){
            queryWrapper.eq("hospitalId",hospital.getHospitalId());
        }
        if(StringUtils.hasText(hospital.getMysqlUrl())){
            queryWrapper.like("mysqlUrl",hospital.getMysqlUrl());
        }
        return hospitalMapper.selectList(queryWrapper);
    }

    public List<HospitalMeta> selectAvailable(HospitalMeta hospital) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null != hospital.getHospitalId() && hospital.getHospitalId()>0){
            queryWrapper.eq("hospitalId",hospital.getHospitalId());
        }
        if(StringUtils.hasText(hospital.getMysqlUrl())){
            queryWrapper.like("mysqlUrl",hospital.getMysqlUrl());
        }
        queryWrapper.eq("mysqlStatus",1);
        return hospitalMapper.selectList(queryWrapper);
    }

    public void addHospital(HospitalMeta hospital) {
        hospitalMapper.insert(hospital);
    }

    public void updateHospital(HospitalMeta hospital) {
        hospitalMapper.updateById(hospital);
    }

    public void deleteHospital(int hospitalId) {
        hospitalMapper.deleteById(hospitalId);
    }

    public List<HospitalMeta> selectBySchema(String mysqlSchema) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mysqlSchema",mysqlSchema);
        return hospitalMapper.selectList(queryWrapper);
    }

    public boolean initDB(HospitalMeta hospital) {
        return InitDBUtil.initDB(hospital.getMysqlUrl(),hospital.getMysqlSchema(),hospital.getMysqlUsername(),hospital.getMysqlPassword());
    }

    public boolean tryConnectDB(HospitalMeta hospital) {
        return InitDBUtil.tryConnectDB(hospital.getMysqlUrl(),hospital.getMysqlSchema(),hospital.getMysqlUsername(),hospital.getMysqlPassword());
    }
}
