package com.zbowen.serialization;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/14 15:28
 */
public class Finally {

    public static void main(String[] args) throws Exception {
        //serializable1();

        //serializable2();

        //反序列化
        FileInputStream fis = new FileInputStream("E:\\Users\\zbowen\\Desktop\\zz.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SingletonFinallyPlus obj = (SingletonFinallyPlus) ois.readObject();
        ois.close();

        System.out.println("serializable1：" + obj);
        System.out.println("serializable1：name = " + obj.name);

        //销毁内部静态引用
        obj.destroy();
        //销毁外部引用
        obj = null;
        //销毁 对象本身
        System.gc();


        //反序列化
        FileInputStream fis1 = new FileInputStream("E:\\Users\\zbowen\\Desktop\\xx.txt");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        SingletonFinallyPlus obj1 = (SingletonFinallyPlus) ois1.readObject();
        ois1.close();
        System.out.println("serializable2：" + obj1);
        System.out.println("serializable2：name = " + obj1.name);

        //再通过 正常方式获取 单例对象
        SingletonFinallyPlus instance = SingletonFinallyPlus.getInstance();
        System.out.println("----------------------------");
        System.out.println("instance：" + instance);
        System.out.println("instance：name = " + instance.name);

    }

    static SingletonFinallyPlus serializable1() throws Exception {
        System.out.println("serializable1序列化第一个对象：");
        //通过 正常的方式 获取单例对象
        SingletonFinallyPlus finallyPlus = SingletonFinallyPlus.getInstance();
        finallyPlus.name = "羞羞羞";
        //序列化这个对象
        FileOutputStream fos = new FileOutputStream("E:\\Users\\zbowen\\Desktop\\zz.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(finallyPlus);
        oos.close();
        System.out.println(finallyPlus);
        System.out.println("name = " + finallyPlus.name);
        System.out.println("----------------------------");
        return finallyPlus;
    }

    static void serializable2() throws InterruptedException {
        System.out.println("serializable2序列化第二个对象：");
        //为了 避免 和 serializable1获得的对象内存地址 相同 使用子线程去完成序列化操作
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //通过 正常的方式 获取单例对象
                    SingletonFinallyPlus finallyPlus = SingletonFinallyPlus.getInstance();
                    finallyPlus.name = "哈哈哈";
                    //序列化这个对象
                    FileOutputStream fos = new FileOutputStream("E:\\Users\\zbowen\\Desktop\\xx.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(finallyPlus);
                    oos.close();
                    System.out.println(finallyPlus);
                    System.out.println("name = " + finallyPlus.name );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

}
