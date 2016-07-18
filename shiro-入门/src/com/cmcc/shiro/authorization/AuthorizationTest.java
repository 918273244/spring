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

//��Ȩ����
public class AuthorizationTest {
    private Logger logger = Logger.getLogger("AuthorizationTest");
    
    //��һ������ʵ��Authorization��Ȩ
    @Test
    public void testAuthorization() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        
        //��֤
        try {
            subject.login(token);
        } catch (Exception e) {
            System.out.println("�û��������������...");
            return;
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤�ɹ���" + isAuthenticated);
        
        /*
         * ��֤�ɹ�����Ȩ
         */
        
        //���ڽ�ɫ����Ȩ
        boolean isHasRole = subject.hasRole("role1");
        System.out.println("�Ƿ��е�����ɫ��" + isHasRole);
        
        boolean isHasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("�Ƿ��ж����ɫ��" + isHasAllRoles);
        
        
        //������Դ����Ȩ
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("�Ƿ��е�����Դ��" + isPermitted);
        
        boolean isPermittedAll = subject.isPermittedAll("user:create", "user:delete");
        System.out.println("�Ƿ��ж����Դ��" + isPermittedAll);
    }
    
    //�������Զ���Realm������Ȩ����
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
            logger.info("�û������������������������...");
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤�ɹ���" + isAuthenticated);
        
        //������Դ����Ȩ(ʵ�ʿ����л��ڽ�ɫ����Ȩ�õĽ���)
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("�Ƿ���Ȩ�ɹ���" + isPermitted);
        
    }
}
