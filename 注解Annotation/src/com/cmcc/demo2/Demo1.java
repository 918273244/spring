package com.cmcc.demo2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

//反射注解
public class Demo1 {

    @Test
    public void test() throws NoSuchMethodException, SecurityException, NoSuchFieldException {
        //反射类的注解
        //1、获取作用目标
        Class clazz = A.class;
        //2、获取指定类型的注解
        MyAnno myAnno = (MyAnno) clazz.getAnnotation(MyAnno.class);
        System.out.println("myAnno.name() = " + myAnno.name() + ", myAnno.age() = " + myAnno.age());
        
        //反射方法的注解
        Method m = clazz.getMethod("fun");
        MyAnno myAnno2 = m.getAnnotation(MyAnno.class);
        System.out.println("myAnno2.name() = " + myAnno2.name() + ", myAnno2.age() = " + myAnno2.age());
        
        //反射成员变量的注解
//        Field field = clazz.getField("arg");
        Field[] fields = clazz.getFields();
        System.out.println("fields = " + fields);
//        MyAnno myAnno3 = field.getAnnotation(MyAnno.class);
//        System.out.println("myAnno3.name() = " + myAnno3.name() + ", myAnno3.age() = " + myAnno3.age());
        
    }
}

@MyAnno(name="A类", age=1)
class A {
    
    @MyAnno(name="arg变量", age=3)
    private int arg;

    @MyAnno(name="fun方法", age=2)
    public void fun() {
        
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String name();
    int age();
}
