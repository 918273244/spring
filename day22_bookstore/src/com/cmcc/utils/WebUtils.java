package com.cmcc.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cmcc.domain.Book;
import com.cmcc.domain.Category;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;

public class WebUtils {

    //泛型方法
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Book upload(HttpServletRequest request, String uploadPath) {
        try {
            Book book = new Book();
            
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list) {
                if(item.isFormField()) {
                    //是普通字段
                    String inputName = item.getFieldName();
                    String value = item.getString("utf-8");
                    if("category_id".equals(inputName)) {
                        BusinessService service = new BusinessServiceImpl();
                        Category c = service.findCategory(value);
                        book.setCategory(c);
                    }else {
                        BeanUtils.setProperty(book, inputName, value);
                    }
                }else {
                    //不是普通字段
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    String savepath = uploadPath;
                    String saveFileName = UUID.randomUUID().toString()+filename;
                    
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(saveFileName);
                    int len = 0;
                    byte[] buff = new byte[1024];
                    while((len = in.read(buff))>0) {
                        out.write(buff, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    book.setImage(saveFileName);
                }
            }
            book.setId(UUID.randomUUID().toString());
            return book;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
