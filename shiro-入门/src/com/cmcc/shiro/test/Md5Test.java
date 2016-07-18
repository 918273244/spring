package com.cmcc.shiro.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

//md5���ܲ���
public class Md5Test {

    public static void main(String[] args) {
        String source = "111111";   //�����ܵ�Դ�ַ���
        String salt = "hehe";       //���ܵ���
        int hashIterations = 2;     //ɢ�еĴ���
        
        /*
         * ����һ��ͨ��Md5Hash()����
         */
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
        String pwd_md5 = md5Hash.toString();
        System.out.println("pwd_md5 = " + pwd_md5);     //pwd_md5 = 3dadd493b95fce3c755a09be28a7dddf
        
        /*
         * ��������ͨ��SimpleHash()����
         */
        SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
        String pwd_simpHash = simpleHash.toString();
        System.out.println("pwd_simpHash = " + pwd_simpHash);       //pwd_simpHash = 3dadd493b95fce3c755a09be28a7dddf
    }
}
