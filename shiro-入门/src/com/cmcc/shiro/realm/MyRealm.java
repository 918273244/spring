package com.cmcc.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //token是用户输入的
        
        //1、从token中取出身份信息
        String userCode = (String) token.getPrincipal();
        
        //2、根据用户输入的userCode从数据库中查询，用户名对应的密码
        //......
        //2.1 模拟从数据库查询到密码
        String password = "111111";
        
        //3、返回认证信息simpleAuthenticationInfo
        //参数：principal：就是上面的userCode； credentials：凭证，即从数据库查询的用户密码；realmName：任意字符串
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, "MyRealm");
        
        return simpleAuthenticationInfo;
    }
    
    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }


}
