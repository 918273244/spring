package com.cmcc.shiro.authorization;

import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

//授权测试
public class AuthorizationTest {
    private Logger logger = Logger.getLogger("AuthorizationTest");
    
    //例一：快速实现Authorization授权
    @Test
    public void testAuthorization() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        
        //认证
        try {
            subject.login(token);
        } catch (Exception e) {
            System.out.println("用户名或者密码错误...");
            return;
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证成功：" + isAuthenticated);
        
        /*
         * 认证成功，授权
         */
        
        //基于角色的授权
        boolean isHasRole = subject.hasRole("role1");
        System.out.println("是否有单个角色：" + isHasRole);
        
        boolean isHasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("是否有多个角色：" + isHasAllRoles);
        
        
        //基于资源的授权
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("是否有单个资源：" + isPermitted);
        
        boolean isPermittedAll = subject.isPermittedAll("user:create", "user:delete");
        System.out.println("是否有多个资源：" + isPermittedAll);
    }
    
    //例二：自定义Realm进行授权测试
    @Test
    public void testMyRealm2() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm2.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            subject.login(token);
        } catch (Exception e) {
            logger.info("用户名或者密码错误，请重新输入...");
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证成功：" + isAuthenticated);
        
        //基于资源的授权(实际开发中基于角色的授权用的较少)
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("是否授权成功：" + isPermitted);
        
    }
}
