package com.cmcc.test;

import org.junit.Test;

public class DaoFactoryTest {
    
    @Test
    public void getSimpleNameTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class c = String.class;
        String simpleName = c.getSimpleName();
        String name = c.getName();
//        Class simpleNameClass = Class.forName(simpleName);
        Class nameClass = Class.forName(name);  //不能用simpleName做参数，必须用全称
        Object instance = (String) nameClass.newInstance();
        System.out.println("+++++++++++++++++String.class = "+c);   //class java.lang.String
        System.out.println("+++++++++++++++++simpleName = "+simpleName);    //String
        System.out.println("+++++++++++++++++name = "+name);  //java.lang.String
        System.out.println("+++++++++++++++++nameClass = "+nameClass);  //class java.lang.String
        System.out.println("+++++++++++++++++instance = "+instance.getClass());  //class java.lang.String
    }
}
