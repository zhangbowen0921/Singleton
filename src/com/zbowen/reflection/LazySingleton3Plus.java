package com.zbowen.reflection;

import java.lang.reflect.Constructor;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 21:27
 */
public class LazySingleton3Plus {

    //添加 volatile进制 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile LazySingleton3Plus instance;

    private LazySingleton3Plus(){
        //synchronized 会阻塞当前 线程 此处的线程锁可以 和 getInstance()中的锁不一致
        synchronized (LazySingleton3Plus.class){
            if (instance != null){
                throw new RuntimeException("非法操作：禁止使用反射攻击创建对象...");
            }
            instance = this;
        }
    }

    public static LazySingleton3Plus getInstance(){
        if (instance == null) {
            synchronized (LazySingleton3Plus.class) {
                if (instance == null) {
                    instance = new LazySingleton3Plus();
                }
            }
        }
        return instance;
    }


    //用于接收 反射 出的对象
    static LazySingleton3Plus instance1, instance2;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //通过反射获取 单例对象
            Class<LazySingleton3Plus> lazyClass = LazySingleton3Plus.class;

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //通过反射获取 单例对象
                    Constructor<LazySingleton3Plus> constructor = null;
                    try {
                        constructor = lazyClass.getDeclaredConstructor();
                        //暴力 执行私有构造器 不执行权限检查
                        constructor.setAccessible(true);
                        //通过 newInstance 获取反射出的对象
                        instance1 = constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //这里使用 了 lambda 表达式简写了 如果不懂请看 t1
            Thread t2 = new Thread(() -> {
                try {
                    Constructor<LazySingleton3Plus> constructor = null;
                    constructor = lazyClass.getDeclaredConstructor();
                    //暴力 执行私有构造器 不执行权限检查
                    constructor.setAccessible(true);
                    //通过 newInstance 获取反射出的对象
                    instance2 = constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("instance1：" + instance1);
            System.out.println("instance2：" + instance2);

            //最多只有 一个对象被反射创建
            if (instance1 != null && instance2 != null) {
                System.out.println("单例模式被破坏");
                break;
            }
        }
    }
}
