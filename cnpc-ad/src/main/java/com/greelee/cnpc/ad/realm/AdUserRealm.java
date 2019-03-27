package com.greelee.cnpc.ad.realm;

import com.greelee.auth.common.properties.AuthProperties;
import com.greelee.cnpc.ad.dao.AdUserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author sqm
 * @version 1.0
 * @date 2019/02/14
 * @description: 类描述: Ad 用户 Realm
 **/
@Component
public class AdUserRealm extends AuthorizingRealm {

    private AuthProperties authProperties;

    private AdUserDao adUserDao;

    @Autowired
    public AdUserRealm(AuthProperties authProperties, AdUserDao adUserDao) {
        this.authProperties = authProperties;
        this.adUserDao = adUserDao;
    }



    /**
     * 获取权限
     * @param principals 权限主体,一般是 username
     * @return 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前用户
        //UserDto user = convertToDto(adUserDao.findUserByUsername((String)principalCollection.getPrimaryPrincipal()));
        //User currentUser = adUserDao.findUserByUsername((String)principalCollection.getPrimaryPrincipal());
        // UserDto user = SecurityUtils.getSubject().getSession().getAttribute("user");

        Object usernameObj = principals.getPrimaryPrincipal();
        //把principals放session中，key=userId value=principals
        // SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (Objects.equals(authProperties.getAdminName(),equals(usernameObj))) {
            info.addStringPermission("*");
        }
        //赋予角色
        // for(RoleDto role:user.getRoles()){
        //     info.addRole(role.getName());
        // }
        //赋予权限
        // List<String> permissionList = resourceShiroDao.listPermissionByUsername(usernameObj);
        // info.addStringPermissions(permissionList);
        // for(PermissionDto permission:user.getPermissions()){
        //System.out.println(permission.getName());
        // info.addStringPermission(permission.getName());
        // }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken token
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * token 强转(向下转型)为用户名密码 token
         */
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // String nickName = "管理员";

        /**
         * 管理员认证
         */
        boolean flag = false;
        if (Objects.equals(authProperties.getAdminName(), username)) {
            flag = Objects.equals(authProperties.getAdminPassword(), password);
        } else {
            /**
             * ad 用户认证,只有在数据库中有此用户才能为 true
             */
            flag = adUserDao.isExistByAccount(username);
        }
        if (flag) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
            SimplePrincipalCollection principals = new SimplePrincipalCollection();
            /**
             * 认证主体
             */
            principals.add(username, getName());
            info.setPrincipals(principals);
            /**
             * 证书
             */
            info.setCredentials(password);
            return info;
        } else {
            throw new UnknownAccountException();
        }

    }


}
