package com.cmcc.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

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

//response的
public class GzipFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        MyResponse myResponse = new MyResponse(response);
        chain.doFilter(request, myResponse);    //过滤器放行。不能直接使用response，因为这样doFilter会直接放行request和response，response就直接输出到浏览器了，而我们并没有捕捉到response处理后的数据
        
        //拿到response处理输出到浏览器的数据
        byte[] buffer = myResponse.getBuffer();
        //将该数据进行压缩
        byte[] gzipBuffer = gzip(buffer);
        
        //将压缩后的数据手动输出给浏览器
        response.setHeader("content-encoding", "gzip");
        response.setHeader("content-length", gzipBuffer.length+"");
        response.getOutputStream().write(gzipBuffer);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
    
    class MyResponse extends HttpServletResponseWrapper {
        private ByteArrayOutputStream bout;
        private HttpServletResponse response;
        
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

        private ByteArrayOutputStream bout;
        
        public MyServletOutputStream(ByteArrayOutputStream bout) {
            this.bout = bout;
        }

        @Override
        public void write(int b) throws IOException {
            bout.write(b);
        }
        
    }
    
    //将数据进行压缩
    public byte[] gzip(byte b[]) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        gout.write(b);
        gout.close();
        
        return bout.toByteArray();
    }

}
