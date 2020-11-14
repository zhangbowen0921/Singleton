package com.zbowen.reflection;

import com.zbowen.LazySingleton2;

import java.lang.reflect.Constructor;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 16:59
 */
public class LazySingleton3 {

    //添加 volatile进制 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile LazySingleton3 instance;

    private LazySingleton3(){
        System.out.println("执行了构造方法");
    }

    public static LazySingleton3 getInstance(){
        if (instance == null) {
            synchronized (LazySingleton3.class) {
                if (instance == null) {
                    instance = new LazySingleton3();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        //通过 调用getInstance() 获取单例对象
        LazySingleton3 instance = LazySingleton3.getInstance();
        //打印单例对象
        System.out.println("instance：" + instance);

        //通过反射获取 单例对象
        Class<LazySingleton3> lazyClass = LazySingleton3.class;
        Constructor<LazySingleton3> constructor = lazyClass.getDeclaredConstructor();
        //暴力 执行私有构造器 不执行权限检查
        constructor.setAccessible(true);
        //通过 newInstance 获取反射出的对象
        LazySingleton3 refInstance = constructor.newInstance();

        //打印反射出的对象
        System.out.println("refInstance：" + refInstance);

        System.out.println("instance == refInstance：" + (instance == refInstance));

    }
}
