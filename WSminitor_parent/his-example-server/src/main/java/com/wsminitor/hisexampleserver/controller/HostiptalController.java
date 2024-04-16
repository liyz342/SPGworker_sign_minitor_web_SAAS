package com.wsminitor.hisexampleserver.controller;


import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsminitor.hisexampleserver.config.DynamicDatasourceConfig;
import com.wsminitor.hisexampleserver.entity.HospitalMeta;
import com.wsminitor.hisexampleserver.service.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* `HostiptalController`是一个Spring MVC控制器，它负责处理与医院信息管理相关的Web请求。这个控制器使用了`@RequestMapping`注解来映射URL路径到具体的方法，并且通过`@ResponseBody`注解来指定返回的数据应该直接写入HTTP响应体中，而不是被解析为跳转路径或视图名称。这样的设计使得控制器能够轻松地与前端框架（如layui）集成，进行数据展示和交互。

以下是`HostiptalController`中定义的一些主要方法及其功能：

1. `selectAll`：分页查询所有医院信息，返回一个包含分页信息和医院数据的JSON对象。

2. `addhospital`：添加一个新的医院信息。在添加之前，它会检查提供的数据库schema是否已经存在，如果存在则返回错误信息，否则调用服务层添加医院信息并返回操作结果。

3. `checkSchema`：一个私有辅助方法，用于检查提供的数据库schema是否已经存在于系统中。

4. `updatehospital`：更新指定医院的信息，如果操作成功则返回成功消息，否则返回异常信息。

5. `deletehospital`：删除指定ID的医院信息，如果操作成功则返回成功消息，否则返回异常信息。

6. `inithospitalDB`：初始化医院的数据库。这个方法会尝试创建一个新的数据库，并更新医院信息的状态。如果数据库创建成功，它还会更新动态数据源配置。

7. `connectDB`：尝试连接到指定医院的数据库，如果连接成功则返回成功消息，否则返回错误信息。

8. `queryHospital`：查询所有医院的信息，并返回一个包含所有医院schema和名称的列表。

这个控制器通过与`HospitalService`服务层的交互来执行业务逻辑，服务层负责处理数据库操作。`DynamicRoutingDataSource`和`DynamicDatasourceConfig`用于动态管理数据源，这是在SaaS应用中实现多租户数据隔离的关键技术。通过动态数据源，每个医院可以拥有自己的数据库schema，而`DynamicRoutingDataSource`负责在运行时根据当前的会话或请求来路由到正确的数据源。

`HostiptalController`的设计体现了Spring框架的依赖注入和面向切面编程（AOP）的原则，同时也展示了如何使用MyBatis Plus和PageHelper插件来简化数据库操作和分页处理。
* */
@Controller
@RequestMapping("hospital")
public class HostiptalController {

    private Logger logger = LoggerFactory.getLogger(HostiptalController.class);

    @Resource
    private HospitalService hospitalService;

    @Resource
    private DynamicDatasourceConfig dynamicDatasourceConfig;

    @Resource
    private DynamicRoutingDataSource datasources;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectAll(Integer page, Integer limit, HospitalMeta hospital){
        PageHelper.startPage(page, limit);
        List<HospitalMeta> allHospital = hospitalService.selectAll(hospital);
        PageInfo pageInfo = new PageInfo(allHospital);
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", pageInfo.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", pageInfo.getList());

        return tableData;
    }

    @RequestMapping("/addhospital")
    @ResponseBody
    public Object addhospital(HospitalMeta hospital){
        try{
            if(checkSchema(hospital.getMysqlSchema())){
                hospitalService.addHospital(hospital);
                return "添加成功";
            }else{
                return "schema已经存在";
            }
        }catch (Exception e){
            return "添加失败，"+e.getMessage();
        }
    }

    private boolean checkSchema(String mysqlSchema) {
        if(hospitalService.selectBySchema(mysqlSchema).size()>0){
            return false;
        }
        return true;
    }

    @RequestMapping("/updatehospital")
    @ResponseBody
    public Object updatehospital(HospitalMeta hospital){
        try{
            hospitalService.updateHospital(hospital);
            return "更新成功";
        }catch (Exception e){
            return "更新失败，"+e.getMessage();
        }
    }

    @RequestMapping("/deletehospital")
    @ResponseBody
    public Object deletehospital(int hospitalId){
        try{
            hospitalService.deleteHospital(hospitalId);
            //删库

            return "删除成功";
        }catch (Exception e){
            return "删除失败，"+e.getMessage();
        }
    }

    @RequestMapping("/inithospitalDB")
    @ResponseBody
    public Object inithospitalDB(HospitalMeta hospital){
        try{
            //建库
            if(hospitalService.initDB(hospital)){
                //更新状态
                hospital.setMysqlStatus(1);
                hospitalService.updateHospital(hospital);

                //更新缓存
                dynamicDatasourceConfig.addDB(hospital);
                return "数据库创建成功";
            }
            return "数据库创建失败，请确认MySQL信息";
        }catch (Exception e){
            return "数据库创建失败，"+e.getMessage();
        }
    }

    @RequestMapping("/connectDB")
    @ResponseBody
    public Object connectDB(HospitalMeta hospital){
        if(hospitalService.tryConnectDB(hospital)){
            return "数据库连接正常";
        }
        return "数据库连接失败，请确认MySQL信息后尝试重建数据库";
    }

    @ResponseBody
    @RequestMapping(value = "/queryHospital",method = RequestMethod.POST)
    public Object queryHospital(){
        List<Map<String ,String>> result = new ArrayList<>();
        for (String hosiptalSchema : datasources.getDataSources().keySet()) {
            Map<String,String> hospitalInfo = new HashMap<>();
            hospitalInfo.put("mysqlSchema",hosiptalSchema);
            hospitalInfo.put("hospitalName", dynamicDatasourceConfig.getHospitalNameBySchema(hosiptalSchema));
            result.add(hospitalInfo);
        }
        return result;
    }
}
