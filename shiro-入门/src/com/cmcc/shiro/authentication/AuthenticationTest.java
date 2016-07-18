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
 * ��½��֤(Authentication)���˳�������
 */
public class AuthenticationTest {
    
    private Logger logger = Logger.getLogger("heihei");

    /*
     * ��һ������ʵ��
     */
    @Test
    public void TestLoginLogout() {
        //1������SecurityManager������ͨ��shiro-users.ini�����ļ�����SecurityManager������
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-users.ini");
        
        //2������securityManager����
        SecurityManager securityManager = factory.getInstance();
        
        //3����securityManager���õ���ǰ�����л�����
        SecurityUtils.setSecurityManager(securityManager);
        
        //4����SecurityUtils�л�ȡsubject
        Subject subject = SecurityUtils.getSubject();
        
        //5������֤ǰ׼��һ��token(����)��ʵ�ʿ����У�����ͨ���û���¼��ȡ��
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            //6��ִ����֤
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("�û������������....");
            return;
        }
        
        //6.1 �Ƿ���֤ͨ��
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤��" + isAuthenticated);
        
        
        try {
            //7��ִ���˳�
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //7.1 �Ƿ��˳�
        isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤��" + isAuthenticated);
    }
    
    /*
     * �������Զ���Realm
     */
    @Test
    public void testMyRealm() {
        /*
         * �����������Realm��д���������ļ�*.ini�еģ���ʵ�ʿ���������Ҫ�����ݿ��л�ȡ�ġ�
         * ��ˣ�������Ի�дһ���Զ���Realm�����������ݿ��л�ȡ����
         */
        
        //1������SecurityManager������ͨ��shiro-realm.ini�����ļ�����SecurityManager������
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        
        SecurityManager securityManager = factory.getInstance();
        
        SecurityUtils.setSecurityManager(securityManager);
        
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        
        try {
            subject.login(token);
        }catch (AuthenticationException e) {
            System.out.println("�û������������....");
            return;
        }
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤��" + isAuthenticated);
    }
    
    //�Զ���realmʵ��ɢ��ƥ��
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
            logger.info("�û����������������������...");
        }
        
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("�Ƿ���֤��" + isAuthenticated);
    }
}
