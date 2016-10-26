public class Test1 {

	public static void bar() {
		return ;
	}

	public static void foo(int a ) {
		if ( a == 0 )
			return ;
		else {
			bar();
			return ;
		}
	}

	public static void main(String[] args) {

		int x = args[0].length();

		System.out.println("Hello\n");
		if (x > 4) {
			if (x > 5)
				bar();
			else
				foo(x);
		}

		System.out.println(x);

		return;
	}

}
