package com.cmcc.asynchronous;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 实现异步处理
 */
@WebServlet(urlPatterns = "/BServlet", asyncSupported = true)   //必须加上"asyncSupported = true"属性才能支持异步处理
public class BServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //0、设置编码。如果不设置编码，经常导致异步失败
	    response.setContentType("text/html;charset=utf-8");
	    //1、得到异步上下文对象
	    AsyncContext asyncContext = request.startAsync(request, response);
	    //2、给上下文对象一个Runnable对象，让它执行这个任务
	    asyncContext.start(new Runnable() {
            
            @Override
            public void run() {
                println("现在马上开始<br/>", response);
                sleep(2000l);
                for(char c = 'A'; c <= 'Z'; c++) {
                    println(c + "", response);
                    sleep(200l);
                }
            }
        });
	    
	}

	public void println(String text, HttpServletResponse response) {
	    try {
	        response.getWriter().println(text);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void sleep(Long ms) {
	    try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
