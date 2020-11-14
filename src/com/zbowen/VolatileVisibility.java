package com.zbowen;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/13 19:00
 */
public class VolatileVisibility extends Thread {

     boolean flag = true;

     volatile int count;

    @Override
    public void run() {
        while (flag){
            count = 9;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileVisibility t1 = new VolatileVisibility();
        t1.start();
        Thread.sleep(100);
        t1.flag = false;
        System.out.println(t1.count);
    }
}
