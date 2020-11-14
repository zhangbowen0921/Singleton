package com.zbowen.serialization;

import java.io.*;

/**
 * @author BW ZHANG
 * @version 1.0
 * @date 2020/11/14 9:26
 */
public class SerializableDetail implements Serializable{

    String name = "SerializableDetail";

    static SerializableDetail DETAIL;

    private static boolean isDestroy = true;

    private SerializableDetail(){

    }

    public static SerializableDetail getInstance(){
        if (DETAIL == null){
            isDestroy = false;
            DETAIL = new SerializableDetail();
        }
        return DETAIL;
    }

    public static void main(String[] args) throws Exception {
        SerializableDetail instance = SerializableDetail.getInstance();
        instance.name = "hello";

        FileOutputStream fos = new FileOutputStream("E:\\Users\\zbowen\\Desktop\\aa.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(instance);
        oos.close();

        System.out.println(instance);
        System.out.println(instance.name);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//              try {
//                  FileOutputStream fos = new FileOutputStream("E:\\Users\\zbowen\\Desktop\\bb.txt");
//                  ObjectOutputStream oos = new ObjectOutputStream(fos);
//                  SerializableDetail instance = SerializableDetail.getInstance();
//                  instance.name = "world";
//                  oos.writeObject(instance);
//                  System.out.println(instance);
//                  System.out.println(instance.name);
//              }catch (Exception e){
//                  e.printStackTrace();
//              }
//            }
//        }).start();
//
//        SerializableDetail instance = SerializableDetail.getInstance();
//        System.out.println(instance);

        instance.destroy();
        instance = null;
        System.gc();

        FileInputStream fis = new FileInputStream("E:\\Users\\zbowen\\Desktop\\aa.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializableDetail o = (SerializableDetail) ois.readObject();

        System.out.println(o);
        System.out.println(o.name);

//        FileInputStream fis2 = new FileInputStream("E:\\Users\\zbowen\\Desktop\\bb.txt");
//        ObjectInputStream ois2 = new ObjectInputStream(fis2);
//        SerializableDetail o2 = (SerializableDetail) ois2.readObject();
//
//        System.out.println(o2);
//        System.out.println(o2.name);
//
//        System.out.println(SerializableDetail.getInstance());

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + "对象被回收");
        isDestroy = true;
        super.finalize();
    }

    public void destroy(){
        DETAIL = null;
    }

    private Object readResolve(){
        System.out.println("readResolve");
        return DETAIL;
    }

    private void writeObject(ObjectOutputStream  out) throws IOException {
        out.writeUTF(name);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        if (!isDestroy) throw new RuntimeException("非法操作：单例对象已创建！");
        if (DETAIL == null){
            name = in.readUTF();
            DETAIL = this;
        }else{
            throw new RuntimeException("非法操作：单例对象已创建！");
        }


    }
}
