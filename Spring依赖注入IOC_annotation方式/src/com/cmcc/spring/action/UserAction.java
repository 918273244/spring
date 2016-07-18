package com.cmcc.spring.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.cmcc.spring.model.User;
import com.cmcc.spring.service.UserService;

//@Component("userAction")
@Controller("userAction")     //@Controller专门用于控制层
@Scope("prototype")         //@Scope相当于<bean scope="singleton"/>，用于控制单例模式还是其他模式
public class UserAction {

    private User user;
    private UserService userService;
    private int id;
    
    public User getUser() {
        return user;
    }

    @Resource       //@Resource默认是通过名称注入，在JSR中提供了@Inject来注入
    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getId() {
        return id;
    }

    @Resource
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
