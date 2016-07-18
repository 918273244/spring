package cn.itcast.ssm.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

//自定义表单认证过滤器FormAuthenticationFilter。
//FormAuthenticationFilter过滤器是用于登陆认证的，但是在有验证码校验的情况下，需要在认证前进行验证码校验，因此通过自定义FormAuthenticationFilter类来实现
//登陆认证是通过FormAuthenticationFilter中的onAccessDenied()方法实现的
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    //原FormAuthenticationFilter中的认证方法
    @Override
    protected boolean onAccessDenied(ServletRequest request,
            ServletResponse response) throws Exception {
        //在这里进行验证码校验
        
        //获取session
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //取出session中的验证码(正确的验证码)
        String validateCode = (String) session.getAttribute("validateCode");
        
        //取出页面的验证码
        String randomcode = httpServletRequest.getParameter("randomcode");
        if(randomcode != null && validateCode != null && !randomcode.equals(validateCode)) {
            httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
            //验证码错误，不再校验账号和密码，拒绝访问
            return true;
        }
        
        //验证码正确，交由父类FormAuthenticationFilter中的onAccessDenied()方法进行账户认证
        return super.onAccessDenied(request, response);
    }

    
}
