package com.zbowen.serialization;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 22:19
 */
public class SingletonSerializablePlus implements Serializable {

    //添加 volatile进制 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile SingletonSerializablePlus instance;

    String name = "hello world";

    private SingletonSerializablePlus(){
        //synchronized 会阻塞当前 线程 此处的线程锁可以 和 getInstance()中的锁不一致
        synchronized (SingletonSerializable.class){
            if (instance != null){
                throw new RuntimeException("非法操作：禁止使用反射攻击创建对象...");
            }
            instance = this;
        }
    }

    public static SingletonSerializablePlus getInstance(){
        if (instance == null) {
            synchronized (SingletonSerializablePlus.class) {
                if (instance == null) {
                    instance = new SingletonSerializablePlus();
                }
            }
        }
        return instance;
    }


    //要求：返回值必须为Object类型 不能写SingletonSerializablePlus
    //方法参数必须为空  权限修饰符没有限制 建议写成 private
    private Object readResolve(){
        return instance;
    }

    public static void main(String[] args) throws Exception {
//        //获取单例对象
//        SingletonSerializablePlus instance = SingletonSerializablePlus.getInstance();
//        instance.name = "张三";
//        //对 单例对象进行 序列化
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\Users\\zbowen\\Desktop\\aa.txt"));
//        oos.writeObject(instance);

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\Users\\zbowen\\Desktop\\aa.txt"));
        SingletonSerializablePlus obj = (SingletonSerializablePlus) ois.readObject();

//        System.out.println("instance：" + instance);
//        System.out.println("instance == obj : " + (instance == obj));
        System.out.println("obj：" + obj);
    }

}
