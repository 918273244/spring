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

//���ڲ�����Ȩ���Զ���Realm
public class MyRealm2 extends AuthorizingRealm {
    
    //��֤
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        String userCode = (String) token.getPrincipal();    //��Ҫ����
        //�������ݿ�...
        //ģ������ݿ��в�ѯ��userCode��Ӧ������
        String password = "111111";
        
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, this.getName());
        return simpleAuthenticationInfo;
    }

    //��Ȩ
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        
        //1����principals�л�ȡ�������Ϣ
        //��getPrimaryPrincipal()��������ֵתΪ��ʵ�������(��"��ʵ�������"������doGetAuthenticationInfo��֤��ͨ��SimpleAuthenticationInfo��������)
        String userCode = (String) principals.getPrimaryPrincipal();    //����Ҫ������
        
        //2���������ݿ�...
        //ģ������ݿ��в�ѯ��userCode��Ӧ��Role��Permission����Ϣ
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");
        permissions.add("user:delete");
        
        //3����ѯ��Ȩ�����ݣ�������Ȩ��Ϣ(Ҫ���������ѯ����Ȩ����Ϣ)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //4���������ѯ������Ȩ��Ϣ��䵽simpleAuthorizationInfo������
        simpleAuthorizationInfo.addStringPermissions(permissions);
        
        //5��������Ȩ��Ϣ(��simpleAuthorizationInfo����)
        return simpleAuthorizationInfo;
    }

}
