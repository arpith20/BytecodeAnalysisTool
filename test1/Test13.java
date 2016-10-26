// bytecode


public class Test13 {
    public static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                x++;
            }
        };
     Thread t2 = new Thread() {
            public void run() {
                x++;
            }
        };
        t1.start();
        t2.start();
       t1.join();
        t2.join();
        System.out.println("x = "+x);
    }
}
