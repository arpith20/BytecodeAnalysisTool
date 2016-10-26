
public class Test10 {
	public static int arr[] = new int[10000];

	public static void main(String[] args) throws InterruptedException {		
		Thread[] ts = new Thread[100];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread() {
				public void run() {
					synchronized (Test10.class) {
						for (int j = 0; j < arr.length; j++) {
							arr[j]++;
						}
					}
				}
			};
		}
		for (int i = 0; i < ts.length; i++) {
			ts[i].start();
		}
		for (int i = 0; i < ts.length; i++) {
			ts[i].join();
		}
	}
}
