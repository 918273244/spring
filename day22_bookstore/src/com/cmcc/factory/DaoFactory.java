package com.cmcc.factory;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static Properties prop = new Properties();  //���ڴ洢�����ļ�����
    //�������ģʽ
    private DaoFactory() {
        //˳����dao�������ļ����й���
        try {
            //ClassLoader *.class.getClassLoader()���ص�����ļ���������ClassLoader��
            //����ClassLoader���з���InputStream getResourceAsStream(String name) ���ض�ȡָ����Դ�����������÷�����������ȡ�ļ�
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
    
    //1�����ͷ���
    //2��Class��������
    public <T> T createDao(Class<T> interfaceClass) {
        try {
            String key = interfaceClass.getSimpleName();      //��ȡ��ļ��
            String className = prop.getProperty(key);       //�洢��dao.properties�е�className����������������������ǰ׺
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
