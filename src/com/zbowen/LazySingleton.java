package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 16:12
 */
public class LazySingleton {

    private static LazySingleton instance;

    //私有 构造 方法
    private LazySingleton() {
        System.out.println(this.getClass().getName() + "构造方法执行了");
    }

    //使用同步方法 加锁
    public static synchronized LazySingleton getInstance(){
        if (instance != null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
