package com.cmcc.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmcc.spring.dao.UserDao;
import com.cmcc.spring.model.User;

//@Component("userService")
@Service("userService")
public class UserService {
    
    private UserDao userDao;
    private int id;
    private List names;
    
    public UserDao getUserDao() {
        return userDao;
    }
    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public int getId() {
        return id;
    }
    @Resource
    public void setId(int id) {
        this.id = id;
    }
    public List getNames() {
        return names;
    }
    @Resource
    public void setNames(List names) {
        this.names = names;
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
