
public class Test12 {
	public static int x;

	public static void main(String[] args) throws InterruptedException {
		Thread[] ts = new Thread[1000];
		
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread() {
				public void run() {
					x = 3;
				}
			};
		}
		x = 2;
		for (int i = 0; i < ts.length; i++) {
			ts[i].start();
		}
		for (int i = 0; i < ts.length; i++) {
			ts[i].join();
		}
	}
}
