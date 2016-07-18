package com.cmcc.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;

//数据压缩的代码编写
public class GZIPDemo {
    //文件压缩
    public void gzipFile(String source, String target) {
        try{
            File in = new File(source);
            File out = new File(target);
            if(!out.exists()){
                out.createNewFile();    //File.mkdir()表示创建文件夹；File.mkdirs()表示创建多层文件夹；File.createNewFile()表示创建文件
            }
            
            FileInputStream fin = new FileInputStream(in);
            FileOutputStream fout = new FileOutputStream(out);
            
            GZIPOutputStream gzout = new GZIPOutputStream(fout);
            
            int len = 0;
            byte buffer[] = new byte[1024];
            while((len = fin.read(buffer))>0){
                gzout.write(buffer, 0, len);
            }
            
            gzout.close();
            fout.close();
            fin.close();
            System.out.println("压缩成功！");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("压缩失败！");
        }
    }
    
    //文件解压
    public void unGzipFile(String source, String target){
        try{
            File in = new File(source);
            File out = new File(target);
            if(!out.exists()){
                out.createNewFile();
            }
            
            FileInputStream fin = new FileInputStream(in);
            FileOutputStream fout = new FileOutputStream(out);
            
            GZIPInputStream gzin = new GZIPInputStream(fin);
            
            int len = 0;
            byte buffer[] = new byte[1024];
            while((len = gzin.read(buffer))>0){
                fout.write(buffer, 0, len);
            }
            
            gzin.close();
            fout.close();
            fin.close();
            System.out.println("解压成功！");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("解压失败！");
        }
    }
    
    //数据压缩
    public void gzipString(String str) {
        try{
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            GZIPOutputStream gzout = new GZIPOutputStream(bout);
            gzout.write(str.getBytes());
            System.out.println(bout.toByteArray());     //数据被压缩到bout流中，可通过toByteArray()方法来获取
            
            System.out.println("压缩成功！");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("压缩失败！");
        }
    }
    
    @Test
    public void gzipFileTest(){
        gzipFile("d:\\RHDSetup.log", "d:\\RHDSetup_zip.log");
    }
    
    @Test
    public void unGzipFileTest(){
        unGzipFile("d:\\RHDSetup_zip.log", "d:\\RHDSetup_unzip.log");
    }
    
    @Test
    public void gzipStringTest(){
        String str = "我是待压缩数据，呵呵！";
        gzipString(str);
    }
}
