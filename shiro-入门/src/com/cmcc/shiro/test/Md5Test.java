package com.cmcc.shiro.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

//md5加密测试
public class Md5Test {

    public static void main(String[] args) {
        String source = "111111";   //待加密的源字符串
        String salt = "hehe";       //加密的盐
        int hashIterations = 2;     //散列的次数
        
        /*
         * 方法一：通过Md5Hash()方法
         */
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
        String pwd_md5 = md5Hash.toString();
        System.out.println("pwd_md5 = " + pwd_md5);     //pwd_md5 = 3dadd493b95fce3c755a09be28a7dddf
        
        /*
         * 方法二：通过SimpleHash()方法
         */
        SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
        String pwd_simpHash = simpleHash.toString();
        System.out.println("pwd_simpHash = " + pwd_simpHash);       //pwd_simpHash = 3dadd493b95fce3c755a09be28a7dddf
    }
}
