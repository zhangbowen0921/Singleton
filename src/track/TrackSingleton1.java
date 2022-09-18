package track;

import teach.hungry.HungrySingleton;
import java.lang.reflect.Constructor;

public class TrackSingleton1 {

    public static void main(String[] args) throws Exception {
        //通过 调用getInstance() 获取单例对象
        HungrySingleton instance = HungrySingleton.getInstance();
        //打印单例对象
        System.out.println("instance：" + instance);

        //通过反射获取 单例对象
        Class<HungrySingleton> lazyClass = HungrySingleton.class;
        Constructor<HungrySingleton> constructor = lazyClass.getDeclaredConstructor();
        //暴力 执行私有构造器 不执行权限检查
        constructor.setAccessible(true);
        //通过 newInstance 获取反射出的对象
        HungrySingleton refInstance = constructor.newInstance();
        //打印反射出的对象
        System.out.println("refInstance：" + refInstance);
        //比较两个对象是否是同一个对象
        System.out.println("instance == refInstance：" + (instance == refInstance));
    }
}
