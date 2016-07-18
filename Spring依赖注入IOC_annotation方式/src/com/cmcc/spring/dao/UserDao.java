package com.cmcc.spring.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cmcc.spring.model.User;

//@Component("userDao")   //相当于完成了<bean id="userDao" class="com.cmcc.spring.dao.UserDao"/>
@Repository("userDao")      //@Repository一般用于DAO注入；而@Component是通用的，dao层、service层都可以使用
public class UserDao {

    public void add(User user) {
        System.out.println("添加了" + user);
    }
    
    public void delete(int id) {
        System.out.println("删除了" + id);
    }
    
    public User load(int id) {
        System.out.println("load" + id);
        return null;
    }
}
