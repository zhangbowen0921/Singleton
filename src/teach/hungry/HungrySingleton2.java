package teach.hungry;

public class HungrySingleton2 {

    // 写法二 使用静态代码块 来实例化对象
    private static HungrySingleton2 instance;

    static {
        instance = new HungrySingleton2();
    }

    //私有构造方法
    private HungrySingleton2() {
        System.out.println(this.getClass().getName() + "构造方法执行了");
    }

    public static HungrySingleton2 getInstance(){
        return instance;
    }

    public static void start() {
        System.out.println("start方法执行了");
    }
}
