package com.cmcc.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WebCacheFilter implements Filter {

    private Map<String, byte[]> map = new HashMap<String, byte[]>();
    
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        //1、得到用户访问的资源url
        String uri = request.getRequestURI();
        
        //2、看map集合中是否保存了该资源
        byte[] resource = map.get(uri);
        
        //3、如果保存了，直接从map中取数据打给浏览器
        if(resource != null){
            response.getOutputStream().write(resource);
            return;
        }
        
        //4、如果没有保存数据，则放行让目标资源执行，这时还需要写一个response的包装类，捕获目标资源的输出
        MyResponse myResponse = new MyResponse(response);
        chain.doFilter(request, myResponse);        //放行
        byte[] data = myResponse.getBuffer();
        
        //5、以资源uri为关键字，把资源的数据保存到map集合中，以备下次访问
        map.put(uri, data);
        
        //6、输出数据给浏览器
        response.getOutputStream().write(data);
    }

    class MyResponse extends HttpServletResponseWrapper {
        private HttpServletResponse response;
        private ByteArrayOutputStream bout;
        
        public MyResponse(HttpServletResponse response) {
            super(response);
            this.response = response;
        }

        //将response处理后的数据存储到ByteArrayOutputStream缓冲区byte[]数组中
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyServletOutputStream(bout);
        }
        
        //获取存储在缓冲区数组byte[]中的数据
        public byte[] getBuffer(){
            return bout.toByteArray();
        }
    }
    
    class MyServletOutputStream extends ServletOutputStream {
        ByteArrayOutputStream bout;
        
        public MyServletOutputStream(ByteArrayOutputStream bout) {
            super();
            this.bout = bout;
        }

        @Override
        public void write(int b) throws IOException {
            bout.write(b);
        }
        
    }
    
    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
