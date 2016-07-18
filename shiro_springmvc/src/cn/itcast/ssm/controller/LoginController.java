package cn.itcast.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.exception.CustomException;
import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.service.SysService;

@Controller
public class LoginController {

    @Autowired
    private SysService sysService;

    /* 
        @RequestMapping("/login")
        public String login(HttpSession session, String randomcode, String usercode,
                String password) throws Exception {
    
            String validateCode = (String) session.getAttribute("validateCode");
    
            if (!randomcode.equals(validateCode)) {
                throw new CustomException("验证码输入错误");
            }
    
            ActiveUser activeUser = sysService.authenticat(usercode, password);
    
            session.setAttribute("activeUser", activeUser);
            return "redirect:/first.action";
        }
    
        @RequestMapping("/logout")
        public String logout(HttpSession session) throws Exception {
    
            session.invalidate();
            return "redirect:/first.action";
    
        }
   
    */
    
    
    // 使用shiro来实现登陆的认证
    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception {

        /*
         * 登陆认证是通过shiro中的Filter拦截后交由自定义Realm实现的。
         * 而login方法中只需要通过获取shiro返回的信息即可判断登陆成功还是失败了
         */

        // 如果登陆失败，会从request中获取认证异常信息。shiroLoginFailure就是shiro异常属性名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName()
                    .equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName()
                    .equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if ("randomCodeError".equals(exceptionClassName)){
                throw new CustomException("验证码错误");
            } else{
                throw new Exception();// 最终在异常处理器生成未知错误
            }
        }

        // 登陆成功，即认证成功。shiro的ShiroFilterFactoryBean中默认会自动跳转至上一页面

        // 登陆失败，返回登陆界面
        return "login";
    }
    
    //退出logout交由shiro中的ShiroFilterFactoryBean过滤器链实现

}
