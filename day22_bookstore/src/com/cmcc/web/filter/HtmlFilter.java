package com.cmcc.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

//过滤恶意上传的代码
public class HtmlFilter implements Filter {

    public void destroy() {
        
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        chain.doFilter(request, response);
    }
    
    class MyRequest extends HttpServletRequestWrapper{

        private HttpServletRequest request;
        
        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        //对request中的getParameter()方法进行增强
        @Override
        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            return filter(value);
        }
        
        //增强的具体方法实现。具体的，这里用于过滤恶意上传代码
        public String filter(String message) {
            if(message == null) {
                return null;
            }
            
            char content[] = new char[message.length()];
            message.getChars(0, message.length(), content, 0);
            StringBuffer result = new StringBuffer(content.length + 50);
            for(int i = 0; i < content.length; i++) {
                switch (content[i]) {
                case '<':
                    result.append("&lt");
                    break;
                case '>':
                    result.append("&gt");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
                }
            }
            return (result.toString());
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        
    }

}
