package com.cmcc.spring.service;

import java.util.List;

import com.cmcc.spring.dao.UserDao;
import com.cmcc.spring.model.User;

public class UserService {
    
    private UserDao userDao;
    private int id;
    private List names;
    
    //getter和setter用来便于注入userDao
    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List getNames() {
        return names;
    }
    public void setNames(List names) {
        this.names = names;
    }
    
    
    //用于构造方法的注入
    public UserService() {
        super();
    }
    public UserService(UserDao userDao) {
        super();
        this.userDao = userDao;
    }
    
    
    public void add(User user) {
        userDao.add(user);
    }
    
    public void delete(int id) {
        userDao.delete(id);
    }
    
    public User load(int id) {
        return userDao.load(id);
    }
    
    
    @Override
    public String toString() {
        return "UserService [userDao=" + userDao + ", id=" + id + ", names="
                + names + "]";
    }

}
