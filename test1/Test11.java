
public class Test11 {
	public static int x;

	public static void main(String[] args) throws InterruptedException {
		final Thread[] ts = new Thread[2000];
	
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread() {
				public void run() {
					int j = x;
					x = ++j;
					if (j >= ts.length)
						return;
					ts[j].start();
					try {
						ts[j].join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}
		ts[0].start();
		ts[0].join();
	}
}
