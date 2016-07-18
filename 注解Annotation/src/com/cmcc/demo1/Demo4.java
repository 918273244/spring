package com.cmcc.demo1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MyAnno6
public class Demo4 {

    @MyAnno6
    public void fun() {
        
    }
}

@Target(value= {ElementType.TYPE, ElementType.METHOD})  //该注解只能在类和方法上使用
@interface MyAnno6 {
    
}
