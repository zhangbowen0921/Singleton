package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 16:32
 */
public class LazySingleton2 {

    private static LazySingleton2 instance;

    //私有 构造 方法
    private LazySingleton2(){}

    public static LazySingleton2 getInstance(){
        //使用同步代码块保证 对象创建的原子性
        synchronized(LazySingleton2.class){
            if (instance == null) {
                instance = new LazySingleton2();
            }
        }
        return instance;
    }
}
