package teach.hungry;

public class Main {

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        System.out.println(instance);
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance2);

    }
}
