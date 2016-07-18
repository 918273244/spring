package com.cmcc.project4;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//java写的windows窗口监听程序
public class WindowsListener {

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.addWindowListener(new MyListener());
    }
}

class MyListener implements WindowListener{
    
    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        Frame f = (Frame) e.getSource();    //getSource()方法获取数据源
        f.dispose();
        System.out.println("窗口关闭了！");
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("窗口打开了");
    }
    
}