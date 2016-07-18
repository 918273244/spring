package com.cmcc.demo1;

@MyAnno
public class Demo1 {

    @MyAnno
    private int id;
    
    @MyAnno
    public void Demo1() {
    }
    
    @MyAnno
    public void fun1() {
    }
    
    public void fun(@MyAnno String param){
        @MyAnno
        String username = "hello";
    }
}

@interface MyAnno {
    
}
