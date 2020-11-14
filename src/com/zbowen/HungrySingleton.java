package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 15:45
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    /* 写法二 使用静态代码块 来创建 对象
    private static HungrySingleton SINGLETON = null;

    static {
        SINGLETON = new HungrySingleton();
    }
    */

    //私有构造方法
    private HungrySingleton() { }

    public static HungrySingleton getInstance(){
        return instance;
    }
}
