package com.zbowen.serialization;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 22:29
 */
public class SingletonFinallyPlus implements Serializable {

    //添加 volatile进制 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile SingletonFinallyPlus instance;

    //确保 序列化uid 和 反序列化的 uid一致 最好还是写上
    private static final long serialVersionUID = 1L;

    String name = "hello";

    private static boolean isDestroy = true;

    private SingletonFinallyPlus(){
        //synchronized 会阻塞当前 线程 此处的线程锁可以 和 getInstance()中的锁不一致
        synchronized (SingletonSerializable.class){
            if (instance != null){
                throw new RuntimeException("非法操作：禁止使用反射攻击创建对象...");
            }
            instance = this;
        }
    }

    public static SingletonFinallyPlus getInstance(){
        if (!isDestroy) throw new RuntimeException("非法操作：单例对象已创建！");
        if (instance == null) {
            synchronized (SingletonFinallyPlus.class) {
                if (instance == null) {
                    isDestroy = false;
                    instance = new SingletonFinallyPlus();
                }
            }
        }
        return instance;
    }

    //当这个类的实例序列化时 会调用该方法 需要手动 将成员变量序列化
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(name);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        synchronized (SingletonFinallyPlus.class){
            if (!isDestroy) throw new RuntimeException("非法操作：单例对象已创建！");
            if (instance == null){
                name = inputStream.readUTF();
                instance = this;
            }else{
                throw new RuntimeException("非法操作：单例对象已创建！");
            }
        }
    }

    //销毁 单例对象 内部的引用
    public void destroy(){
        instance = null;
    }

    @Override
    protected void finalize() throws Throwable {
        isDestroy = true; //执行这里 对象才真正被销毁
        System.out.println("----------------------------");
        System.out.println(this + "被销毁");
        System.out.println("----------------------------");
        super.finalize();
    }

}
