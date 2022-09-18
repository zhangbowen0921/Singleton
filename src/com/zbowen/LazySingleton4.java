package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 19:55
 */
public class LazySingleton4 {

    //私有构造
    public LazySingleton4() { }

    //使用内部类的静态变量来实现单例模式
    private static class LazyHolder {
        private static final LazySingleton4 INSTANCE = new LazySingleton4();
    }

    //只有 第一次调用该方法 才会导致 内部类 的加载和初始化其成员变量
    public static LazySingleton4 getInstance(){
        return LazyHolder.INSTANCE;
    }

    public static void start() {
        System.out.println("start方法执行了");
    }
}

