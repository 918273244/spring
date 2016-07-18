package com.cmcc.project5;

public class Demo {

    public static void main(String[] args) {
        Person p = new Person();
        p.eat();
        p.run();
        
        p.addListener(new MyListener());
        p.eat();
        p.run();
    }
}

class MyListener implements PersonListener{

    @Override
    public void doRun(PersonEvent e) {
        System.out.println("监控器的run");
    }

    @Override
    public void doEat(PersonEvent e) {
        System.out.println("监控器的eat");
    }
}