package com.cmcc.spring.action;

import com.cmcc.spring.model.User;
import com.cmcc.spring.service.UserService;

public class UserAction {

    private User user;
    private UserService userService;
    private int id;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add() {
        userService.add(user);
    }
    
    public void delete() {
        userService.delete(id);
    }
    
    public void load() {
        userService.load(id);
    }
}
