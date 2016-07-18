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

    //������֤
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //token���û������
        
        //1����token��ȡ�������Ϣ
        String userCode = (String) token.getPrincipal();
        
        //2�������û������userCode�����ݿ��в�ѯ���û�����Ӧ������
        //......
        //2.1 ģ������ݿ��ѯ������
        String password = "111111";
        
        //3��������֤��ϢsimpleAuthenticationInfo
        //������principal�����������userCode�� credentials��ƾ֤���������ݿ��ѯ���û����룻realmName�������ַ���
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, "MyRealm");
        
        return simpleAuthenticationInfo;
    }
    
    //������Ȩ
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }


}
