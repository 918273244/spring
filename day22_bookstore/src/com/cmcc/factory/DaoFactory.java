package com.cmcc.factory;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static Properties prop = new Properties();  //用于存储配置文件的类
    //单例设计模式
    private DaoFactory() {
        //顺便与dao的配置文件进行关联
        try {
            //ClassLoader *.class.getClassLoader()返回的是类的加载器对象ClassLoader。
            //该类ClassLoader中有方法InputStream getResourceAsStream(String name) 返回读取指定资源的输入流。该方法可用来读取文件
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("com/cmcc/factory/dao.properties");
            prop.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static final DaoFactory instance = new DaoFactory();
    public static DaoFactory getInstance() {
        return instance;
    }
    
    //1、泛型方法
    //2、Class类做参数
    public <T> T createDao(Class<T> interfaceClass) {
        try {
            String key = interfaceClass.getSimpleName();      //获取类的简称
            String className = prop.getProperty(key);       //存储在dao.properties中的className必须是完整的类名，包含前缀
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
