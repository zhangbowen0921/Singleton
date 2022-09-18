package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 16:59
 */
public class LazySingleton3 {

//    private static LazySingleton3 instance;

    //添加 volatile禁止 instance对象创建时的指令重排 从而保证程序的正确性
    private static volatile LazySingleton3 instance;

    private LazySingleton3(){}

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
}


