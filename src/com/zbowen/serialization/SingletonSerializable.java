package com.zbowen.serialization;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 21:27
 */
public class SingletonSerializable implements Serializable {

    //添加 volatile进制 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile SingletonSerializable instance;

    String name = "Hello World";

    private SingletonSerializable(){
        System.out.println("构造方法执行了");
        //synchronized 会阻塞当前 线程 此处的线程锁可以 和 getInstance()中的锁不一致
        synchronized (SingletonSerializable.class){
            if (instance != null){
                throw new RuntimeException("非法操作：禁止使用反射攻击创建对象...");
            }
            instance = this;
        }
    }

    public static SingletonSerializable getInstance(){
        if (instance == null) {
            synchronized (SingletonSerializable.class) {
                if (instance == null) {
                    instance = new SingletonSerializable();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) throws Exception {
        //获取单例对象
        SingletonSerializable instance = SingletonSerializable.getInstance();
        instance.name = "张三";
        //对 单例对象进行 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/zhangbowen/aa.txt"));
        oos.writeObject(instance);

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/zhangbowen/aa.txt"));
        SingletonSerializable obj = (SingletonSerializable) ois.readObject();

        System.out.println("instance：" + instance);
        System.out.println("instance.name：" + instance.name);
        System.out.println("obj：" + obj);
        System.out.println("obj.name：" + obj.name);
        System.out.println("instance == obj : " + (instance == obj));
    }

}
