package com.cmcc.demo1;

public class Teacher {

    private int id;
    private String name;
    private User user;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", user=" + user + "]";
    }
}
