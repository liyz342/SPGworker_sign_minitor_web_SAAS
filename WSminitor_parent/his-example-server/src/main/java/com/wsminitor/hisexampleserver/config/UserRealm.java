package com.wsminitor.hisexampleserver.config;


import com.wsminitor.hisexampleserver.entity.User;
import com.wsminitor.hisexampleserver.service.MenuService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
/*
* 这段代码定义了一个名为`UserRealm`的类，它继承自Apache Shiro框架中的`AuthorizingRealm`类。在Shiro安全框架中，`Realm`是一个核心概念，用于实现数据源与Shiro的安全模型之间的桥梁。`UserRealm`类主要用于自定义用户的认证（登录验证）和授权（权限分配）逻辑。

以下是`UserRealm`类的主要组成部分和功能：

1. **成员变量**:
   - `menuService`: 通过Spring的`@Autowired`注解自动注入的`MenuService`实例，用于处理与用户菜单相关的业务逻辑。

2. **认证方法** (`doGetAuthenticationInfo`):
   - 当用户尝试登录时，Shiro会调用这个方法来进行认证。
   - 方法从`AuthenticationToken`中提取用户名（`getPrincipal`）。
   - 通过`menuService.loginname`方法查询用户信息，这里假设该方法返回`User`实体，包含用户的详细信息。
   - 如果用户不存在，方法返回`null`，Shiro会抛出`AuthenticationException`。
   - 如果用户存在，使用`SimpleAuthenticationInfo`创建一个认证信息对象，包含用户对象、密码、盐值（用于加密）和realm名称（`getName()`）。
   - 返回认证信息对象，Shiro将使用这些信息来验证用户的凭据。

3. **授权方法** (`doGetAuthorizationInfo`):
   - 当用户登录成功后，Shiro会调用这个方法来确定用户的权限。
   - 在当前的实现中，这个方法返回`null`，这意味着没有为用户分配任何权限。
   - 在实际应用中，你需要实现这个方法来返回用户的权限信息，例如角色和权限列表。

这个类的设计理念是将Shiro的认证和授权逻辑与应用程序的业务逻辑分离。通过继承`AuthorizingRealm`并重写相应的方法，开发者可以根据自己的需求来实现用户认证和授权的逻辑，同时保持代码的可维护性和可扩展性。

在实际应用中，你可能需要实现`doGetAuthorizationInfo`方法来返回用户的权限信息，并在`doGetAuthenticationInfo`方法中添加额外的逻辑来处理错误情况，例如用户被锁定或账户过期等。此外，为了提高安全性，你可能还需要考虑使用更安全的密码存储和验证机制，如使用bcrypt或scrypt算法替代Shiro默认的加密方法。
* */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private MenuService menuService;

    /*
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取的前台username
        String username = (String) authenticationToken.getPrincipal();

        User sysUser = null;
        try {
            sysUser = menuService.loginname(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断对象是否有值
        if (sysUser == null) {
            return null;
        }
        //密码不需要我们比对，shiro会给我们比对                      //对象    //获取前台密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPwd(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        return info;

    }

    /*
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
