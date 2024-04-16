package com.wsminitor.hisexampleserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsminitor.hisexampleserver.entity.Area;
import com.wsminitor.hisexampleserver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 这段代码是一个使用Spring框架编写的Java类，它定义了一个名为`AreaController`的控制器，用于处理与生产地址（Area）相关的Web请求。这个控制器使用了`PageHelper`分页插件来处理分页请求，并返回前端框架（如layui）所需的数据格式。下面是对这段代码的详细解释：

1. **类定义和依赖注入**：
   - `@Controller`注解表明这个类是一个Spring MVC控制器。
   - `@RequestMapping("area")`定义了这个控制器处理的URL路径前缀。
   - `@Autowired`注解用于自动注入`AreaService`类的实例，这个服务类负责处理业务逻辑。

2. **方法定义**：
   - `findAllArea`方法用于查询所有的生产地址。它接受一个`Area`对象和一个分页参数（`page`和`limit`），然后通过`PageHelper`进行分页处理。返回的数据格式是按照layui框架的要求组织的，包含了状态码（`code`）、消息（`msg`）、总数据量（`count`）和分页数据（`data`）。
   - `addArea`方法用于添加一个新的生产地址。它首先检查数据库中是否已存在相同的地址，如果不存在，则添加新地址并返回操作结果。
   - `deleteArea`方法用于删除一个生产地址。它接受一个地址ID作为参数，然后执行删除操作并返回结果。

3. **数据结构和返回值**：
   - `Area`类是一个实体类，代表生产地址的数据模型。
   - `PageInfo`是`PageHelper`提供的一个类，用于封装分页查询的结果。
   - 方法的返回值是一个`Map`对象，它包含了前端需要的数据和状态信息。

这段代码是一个典型的Spring MVC控制器，它通过注解和分页插件来简化Web请求的处理和数据的返回。这个控制器可以很容易地集成到一个基于Spring框架的Web应用程序中，用于管理生产地址的增删查改操作。
* */
@Controller
@RequestMapping("area")
public class AreaController {
    @Autowired
    private AreaService areaService;
    /*
     * 查询生产地址
     * */
    @RequestMapping("findAllArea")
    @ResponseBody
    public Object AreaList(Area Area, Integer page, Integer limit){
        PageHelper.startPage(page, limit);
        List<Area> listAll = areaService.findAllArea(Area);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> tableData = new HashMap<String, Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", pageInfo.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", pageInfo.getList());

        return tableData;
    }
    /*
     * 添加生产地址
     * */
    @RequestMapping("addArea")
    @ResponseBody
    public Object addArea(Area Area){
        int count = areaService.count(Area);
        if(count==0){
            int i = areaService.addArea(Area);
            if(i==1){
                return "添加成功";
            }else{
                return "添加失败";
            }
        }else {
            return Area.getAreaName()+"已存在";
        }

    }

    /*
     * 删除生产地址
     * */
    @RequestMapping("deleteArea")
    @ResponseBody
    public Object deleteArea(Integer areaId){
        int i = areaService.deleteArea(areaId);
        if(i==1){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}
