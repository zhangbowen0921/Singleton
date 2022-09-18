package teach.hungry;

public class HungrySingleton {

    // 写法一 声明对象时直接实例化对象
    private static HungrySingleton instance = new HungrySingleton();

    //私有构造方法
    private HungrySingleton() {
        System.out.println(this.getClass().getName() + "构造方法执行了");
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

    public static void start() {
        System.out.println("start方法执行了");
    }

}
