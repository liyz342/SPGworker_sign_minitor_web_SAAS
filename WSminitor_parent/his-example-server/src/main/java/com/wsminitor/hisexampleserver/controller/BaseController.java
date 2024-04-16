package com.wsminitor.hisexampleserver.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
/*
* 这段代码定义了一个名为`BaseController`的类，它是一个Spring MVC的控制器基类，用于处理日期格式化的问题。在Spring的表单处理中，客户端提交的字符串需要转换为后端Java对象的属性类型，这个过程称为数据绑定（data binding）。`BaseController`通过`@InitBinder`注解定义了一个初始化绑定器（`initBinder`）的方法，这个方法在数据绑定过程中被调用，用于自定义日期格式的转换。

下面是对这段代码的详细解释：

1. **类定义**：
   - `BaseController`类可能被其他控制器继承，提供了一个统一的日期格式化处理机制。
2. **`@InitBinder`注解**：
   - 这个注解用于指定一个方法，该方法会在Web请求的参数绑定到Java对象之前被调用。这个方法可以用来自定义数据绑定的行为。

3. **`initBinder`方法**：
   - 这个方法接收一个`ServletRequestDataBinder`对象作为参数，这个对象负责将请求参数绑定到Java对象上。
   - 方法内部创建了一个`SimpleDateFormat`对象，定义了日期时间的格式为"yyyy-MM-dd hh:mm:ss"。
   - 通过调用`binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true))`，注册了一个自定义的编辑器`CustomDateEditor`，用于处理`Date`类型的字段。`CustomDateEditor`使用`SimpleDateFormat`来解析和格式化日期。

通过这种方式，当表单提交日期字符串时，`CustomDateEditor`会按照指定的格式"yyyy-MM-dd hh:mm:ss"来解析这些字符串，并将它们转换为`Date`对象。这避免了因为日期格式不一致而导致的绑定错误，确保了数据的准确性和一致性。

`BaseController`可以被其他控制器类继承，这样所有继承自`BaseController`的控制器都会使用相同的日期格式化规则，从而保持了整个应用程序的一致性。
* */
public class BaseController {
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
