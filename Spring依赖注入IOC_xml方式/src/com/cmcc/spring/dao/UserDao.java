package com.cmcc.spring.dao;

import com.cmcc.spring.model.User;

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
