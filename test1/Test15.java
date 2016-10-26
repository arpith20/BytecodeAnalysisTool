// bytecode



public class Test15 {
    public static int x = 0;
    public final static Object lock = new Object();
    public final static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                synchronized (lock) {
                    x++;
                }
            }
        };
        t1.start();
        synchronized (lock2) {
            x++;
        }
        t1.join();
        System.out.println("x = "+x);
    }
}
