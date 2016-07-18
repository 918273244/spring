package com.cmcc.shiro.authentication;

import java.util.logging.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/*
 * 登陆认证(Authentication)、退出的例子
 */
public class AuthenticationTest {
    
    private Logger logger = Logger.getLogger("heihei");

    /*
     * 例一：快速实现
     */
    @Test
    public void TestLoginLogout() {
        //1、创建SecurityManager工厂。通过shiro-users.ini配置文件创建SecurityManager工厂。
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-users.ini");
        
        //2、创建securityManager对象
        SecurityManager securityManager = factory.getInstance();
        
        //3、将securityManager设置到当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        
        //4、从SecurityUtils中获取subject
        Subject subject = SecurityUtils.getSubject();
        
        //5、在认证前准备一个token(令牌)。实际开发中，这是通过用户登录获取的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            //6、执行认证
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("用户名或密码错误....");
            return;
        }
        
        //6.1 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证：" + isAuthenticated);
        
        
        try {
            //7、执行退出
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //7.1 是否退出
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证：" + isAuthenticated);
    }
    
    /*
     * 例二：自定义Realm
     */
    @Test
    public void testMyRealm() {
        /*
         * 上面的例子中Realm是写死在配置文件*.ini中的，而实际开发中试需要从数据库中获取的。
         * 因此，这个测试会写一个自定义Realm，用来从数据库中获取数据
         */
        
        //1、创建SecurityManager工厂。通过shiro-realm.ini配置文件创建SecurityManager工厂。
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        
        SecurityManager securityManager = factory.getInstance();
        
        SecurityUtils.setSecurityManager(securityManager);
        
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            subject.login(token);
        }catch (AuthenticationException e) {
            System.out.println("用户名或密码错误....");
            return;
        }
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证：" + isAuthenticated);
    }
    
    //自定义realm实现散列匹配
    @Test
    public void testMyRealmMd5() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            subject.login(token);
        } catch (Exception e) {
            logger.info("用户名或密码错误，请重新输入...");
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证：" + isAuthenticated);
    }
}
