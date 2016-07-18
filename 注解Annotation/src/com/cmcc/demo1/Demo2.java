package com.cmcc.demo1;


@MyAnno1(age=10, name="lisi")
@MyAnno2(name="wangwu")
@MyAnno3(100)
public class Demo2 {

}

@interface MyAnno1 {
    int age();
    String name();
}

@interface MyAnno2 {
    int age() default 100;
    String name();
}

@interface MyAnno3 {
    int value();
    String name() default "zhangsan";
}
