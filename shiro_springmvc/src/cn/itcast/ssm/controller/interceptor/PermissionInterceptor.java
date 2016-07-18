package cn.itcast.ssm.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.po.SysPermission;
import cn.itcast.ssm.util.ResourcesUtil;

public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURI();
		
		List<String> open_urls = ResourcesUtil.gekeyList("anonymousURL");
		for(String open_url:open_urls){
			if(url.indexOf(open_url)>=0){
				return true;
			}
		}
		
		List<String> common_urls = ResourcesUtil.gekeyList("commonURL");
		for(String common_url:common_urls){
			if(url.indexOf(common_url)>=0){
				return true;
			}
		}
		
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		List<SysPermission> permissions = activeUser.getPermissions();
		for(SysPermission sysPermission:permissions){
			String permission_url = sysPermission.getUrl();
			if(url.indexOf(permission_url)>=0){
				return true;
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor1...postHandle");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1...afterCompletion");
	}

}
