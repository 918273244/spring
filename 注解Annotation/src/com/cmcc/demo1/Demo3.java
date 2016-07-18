package com.cmcc.demo1;

@MyAnno4(
        a=10,
        b="张三",
        c=MyEnum.C,
        d=String.class,
        e=@MyAnno5(),   //注解作为注解的属性，需要加上括号
        f= {"zhangsan", "lisi"}
        )
public class Demo3 {

}

@interface MyAnno4 {
    int a();
    String b();
    MyEnum c();
    Class d();
    MyAnno5 e();        //注解作为注解的属性
    String[] f();
}

@interface MyAnno5 {
    
}

enum MyEnum{
    A, B, C
}