package com.cmcc.demo2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

//获取泛型的子类类型
public class getSonClass {
    @Test
    public void test() {
        AA1 aa1 = new AA1();
        aa1.getSonClassType();
        
        AA2 aa2 = new AA2();
        aa2.getSonClassType();
    }
}

class A<T>{
    public void getSonClassType() {
        /*
         * 该方法是为了获取子类型传递的泛型信息，即要得到一个Class
         */
        Class clazz = this.getClass();    //得到子类的类型。注意，实例化后这里的this表示的是子类，而不再是父类
        Type type = clazz.getGenericSuperclass();   //获取传递给父类参数化类型
        ParameterizedType pType = (ParameterizedType) type; //就是A<String>
        Type[] types = pType.getActualTypeArguments();  //就是一个Class数组
        Class sonClassType = (Class)types[0];   //子类的类型
        System.out.println("子类的类型为：" + sonClassType);
    }
}

class AA1 extends A<String>{
    
}

class AA2 extends A<Integer>{
    
}