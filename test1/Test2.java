public class Test2 {

	public static void main(String[] args) {

		int x = args[0].length();
		int y = 0;

		while (x < 10) {
			y = 0;
			while (y < 10)
				y++;
			x++;
		}

		System.out.println(x);

		return;
	}

}
