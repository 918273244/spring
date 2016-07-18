package com.cmcc.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//用于测试授权的自定义Realm
public class MyRealm2 extends AuthorizingRealm {
    
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        String userCode = (String) token.getPrincipal();    //需要返回
        //链接数据库...
        //模拟从数据库中查询到userCode对应的密码
        String password = "111111";
        
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, this.getName());
        return simpleAuthenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        
        //1、从principals中获取主身份信息
        //将getPrimaryPrincipal()方法返回值转为真实身份类型(该"真实身份类型"是上面doGetAuthenticationInfo认证中通过SimpleAuthenticationInfo对象填充的)
        String userCode = (String) principals.getPrimaryPrincipal();    //不需要返回了
        
        //2、链接数据库...
        //模拟从数据库中查询到userCode对应的Role、Permission等信息
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");
        permissions.add("user:delete");
        
        //3、查询到权限数据，返回授权信息(要包含上面查询到的权限信息)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //4、将上面查询到的授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        
        //5、返回授权信息(即simpleAuthorizationInfo对象)
        return simpleAuthorizationInfo;
    }

}
