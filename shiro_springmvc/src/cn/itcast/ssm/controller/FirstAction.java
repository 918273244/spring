package cn.itcast.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.po.SysPermission;


@Controller
public class FirstAction {
	//系统首页
	@RequestMapping("/first")
	public String first(Model model)throws Exception{
		
	    //获取登陆用户
        Subject subject = SecurityUtils.getSubject();  //从shiro的session中获取登陆用户  
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        
        //自定义realm中doGetAuthenticationInfo方法中返回的Principal是ActiveUser类，因此可以直接进行强转
        
        model.addAttribute("activeUser", activeUser);
        return "/first";
	}
	
	//欢迎页面
	@RequestMapping("/welcome")
	public String welcome(Model model)throws Exception{
		
		return "/welcome";
		
	}
}	
