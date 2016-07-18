package cn.itcast.ssm.shiro;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.po.SysPermission;
import cn.itcast.ssm.po.SysUser;
import cn.itcast.ssm.service.SysService;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysService sysService;
    
    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //token是用户输入的
        
        //1、从token中取出身份信息
        String userCode = (String) token.getPrincipal();
        
        //2、根据用户输入的userCode从数据库中查询，用户名对应的密码
        SysUser sysUser = null;
        try {
            sysUser = sysService.findSysUserByUserCode(userCode);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if(sysUser == null) {
            return null;
        }
        String password = sysUser.getPassword();
        String salt = sysUser.getSalt();
        
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUsername(sysUser.getUsername());
        List<SysPermission> menus = null;
        try {
            menus = sysService.findMenuListByUserId(sysUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        activeUser.setMenus(menus);
        
        //3、返回认证信息simpleAuthenticationInfo
        //参数：principal：就是待返回的信息，是任意Object类的子类； credentials：凭证，即从数据库查询的用户密码；realmName：任意字符串
        //具体的用户名和密码认证是通过shiro框架中的凭证匹配器org.apache.shiro.authc.credential.HashedCredentialsMatcher来实现的，只需要将查询到的密码、盐返回即到simpleAuthenticationInfo中即可
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password, ByteSource.Util.bytes(salt), "MyRealm");
        
        return simpleAuthenticationInfo;
    }
    
    //用于授权
    //碰到需要权限的地方，如@RequirePermissions和<shiro:hasPermission>，shiro框架就会调用realm获取数据库中的权限信息，查看是否包含需要的权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        
        //获取登录人身份
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        
        //根据登录人身份信息查询数据库
        List<SysPermission> sysPermissions = null;
        try {
            sysPermissions = sysService.findPermissionListByUserId(activeUser.getUserid());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if(sysPermissions == null) {
            return null;
        }
        
        List<String> permissions = new ArrayList<String>();
        for(SysPermission sysPermission : sysPermissions) {
            permissions.add(sysPermission.getPercode());
        }
        
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //手动清除缓存应该调用该方法
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
