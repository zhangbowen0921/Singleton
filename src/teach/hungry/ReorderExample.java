package teach.hungry;

/**
 * 指令重排演示
 */
public class ReorderExample {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    private static void start() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + x + "," + y + ")");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            start();
        }
    }

}