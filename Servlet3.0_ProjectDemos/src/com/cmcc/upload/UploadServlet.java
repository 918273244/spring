package com.cmcc.upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
 * 上传文件
 */
@WebServlet(urlPatterns = "/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //1、getParameter()方法可以使用了
        String username = request.getParameter("username");
        //2、获取文件表单字段，对应的Part对象
        Part part = request.getPart("resume");
        //3、从part中获取需要的数据
        //3.1 获取上传文件的MIME类型
        System.out.println(part.getContentType());
        //3.2 获取上传文件的大小
        System.out.println(part.getSize());
        //3.3 获取上传文件的字段名称
        System.out.println(part.getName());
        //3.4 这个头中包含了上传文件的名字
        System.out.println(part.getHeader("Content-Disposition"));
        //3.5 保存上传文件
        part.write("d://xxx.jpg");
        
        //4、获取上传的文件名(文件名信息存储在"Content-Disposition"头中)
        String header = part.getHeader("Content-Disposition");  //输出：header = form-data; name="resume"; filename="王蛋蛋.jpg"
        int index = header.lastIndexOf("filename=\"");
        String fileName = header.substring(index + 10, header.length() - 1);
        System.out.println("fileName = " + fileName);
	}

}
