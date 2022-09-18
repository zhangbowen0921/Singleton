package com.zbowen;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 20:12
 */
public enum EnumSingleton implements Serializable {

    INSTANCE;

    public void doTask(){
        //TODO 任务方法
        System.out.println("do something...");
    }

    public static void main(String[] args) throws Exception {
        //枚举 对象 序列化
        EnumSingleton instance = EnumSingleton.INSTANCE;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/zhangbowen/aa.txt"));
        oos.writeObject(instance);

        //枚举 对象 的反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/zhangbowen/aa.txt"));
        EnumSingleton obj = (EnumSingleton) ois.readObject();
        ois.close();
        System.out.println(EnumSingleton.INSTANCE == obj);
    }
}
