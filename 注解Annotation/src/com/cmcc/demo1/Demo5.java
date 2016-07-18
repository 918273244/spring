package com.cmcc.demo1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MyAnno7
public class Demo5 {

}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno7 {
    
}