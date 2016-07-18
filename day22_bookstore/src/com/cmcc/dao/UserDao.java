package com.cmcc.dao;

import com.cmcc.domain.User;

public interface UserDao {
    public void add(User user);
    public User find(String id);
    public User find(String username,String password);
}
