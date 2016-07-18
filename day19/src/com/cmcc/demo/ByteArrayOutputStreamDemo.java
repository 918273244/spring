package com.cmcc.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

//ByteArrayOutputStream类的使用。本质上就是操作缓冲区byte[]数组
public class ByteArrayOutputStreamDemo {
    /*
     * ByteArrayOutputStream类在创建对象的时候，程序会创建一个byte[]类型的数组缓冲区，
     * 类ByteArrayOutputStream和ByteArrayInputStream本质上就是在操作该数组缓冲区
     * 来写入或读出数据。
     */
    
    @Test
    public void ByteArrayOutputStreamTest() throws IOException{
        String data = "hehehehe";
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();   //在缓冲区创建一个byte[]数组，用于存储数据
        bout.write(data.getBytes());    //将数据存储到byte[]缓冲区中
        byte[] dataByteArray = bout.toByteArray();  //将缓冲区中byte[]数组中的数据取出来
        
        System.out.println(dataByteArray.toString());
    }
}
