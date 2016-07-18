package com.cmcc.project5;

//观察者设计模式(observer设计模式)
//用于在自定义类中为了方便将来能添加监听器，这个自定义类应该怎么定义
public class Person {
    
    private PersonListener listener;
    
    public void run() {
        if(listener != null) {
            PersonEvent e = new PersonEvent(this);
            this.listener.doRun(e);
            System.out.println("run，使用Listener++++++++");
        } else {
            System.out.println("run，未使用Listener---------");
        }
    }
    
    public void eat() {
        if(listener != null) {
            PersonEvent e = new PersonEvent(this);
            this.listener.doEat(e);
            System.out.println("eat, 使用Listener++++++++");
        } else {
            System.out.println("eat, 未使用Listener---------");
        }
    }
    
    //注册监听器
    public void addListener(PersonListener listener) {
        this.listener = listener;
    }
}

interface PersonListener{
    public void doRun(PersonEvent e);
    public void doEat(PersonEvent e);
}

//Event事件
class PersonEvent{
    private Person person;

    public PersonEvent() {
        super();
    }

    public PersonEvent(Person person) {
        super();
        this.person = person;
    }
    
}
