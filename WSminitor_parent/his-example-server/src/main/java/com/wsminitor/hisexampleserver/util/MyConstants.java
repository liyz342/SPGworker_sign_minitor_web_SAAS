package com.wsminitor.hisexampleserver.util;
/*
* `MyConstants`类是一个简单的Java实用工具类，它定义了一些常量值，这些常量通常用于配置或在应用程序中的多个地方使用。在这个类中，定义了一个静态常量`SESSION_HOSPITAL_SCHEMA`，其值为`"hospitalSchema"`。

这个常量可能用于表示HTTP会话（Session）中存储的与医院相关的数据库schema名称的键（key）。在SaaS（Software as a Service）应用程序中，每个医院客户可能需要自己的数据库schema来保持数据隔离。因此，当用户登录系统时，他们的医院schema可能会被存储在会话中，以便在执行数据库操作时能够动态地选择正确的数据源。

例如，在`CCashierServiceImpl`类中，`@DS`注解使用了`#session.hospitalSchema`表达式来指定数据源路由的逻辑。这个表达式引用了会话中的`hospitalSchema`属性，它的值就是通过`MyConstants.SESSION_HOSPITAL_SCHEMA`常量来引用的。

使用这样的常量可以提高代码的可维护性和可读性，因为如果需要修改键名，你只需要在`MyConstants`类中更改一次，而不需要在代码的多个地方进行更改。此外，它也有助于避免魔法字符串（magic strings）的使用，魔法字符串是指那些直接硬编码在代码中的字符串，它们可能会导致代码难以理解和维护。通过使用常量，你可以为这些字符串赋予有意义的名称，从而提高代码质量。
* */
public class MyConstants {

    public static final String SESSION_HOSPITAL_SCHEMA="hospitalSchema";
}
