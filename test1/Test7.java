/*op
	 [java] .limit stack 2
     [java] .limit locals 2
     [java] iload_1
     [java] iconst_2
     [java] if_icmpne label0
     [java] 
     [java] 2
     [java] getstatic java/lang/System/out Ljava/io/PrintStream;
     [java] iload_1
     [java] invokevirtual java/io/PrintStream/println(I)V
     [java] goto label1
     [java] 
     [java] .limit stack 2
     [java] .limit locals 2
     [java] iload_1
     [java] iconst_2
     [java] if_icmpne label0
     [java] 
     [java] 1
     [java] 1
     [java] getstatic java/lang/System/out Ljava/io/PrintStream;
     [java] iload_1
     [java] invokevirtual java/io/PrintStream/println(I)V
     [java] getstatic java/lang/System/out Ljava/io/PrintStream;
     [java] iload_1
     [java] invokevirtual java/io/PrintStream/println(I)V
*/

import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


class Test7{
	public static void main(String[] argv) throws Exception
	{
		//Scanner in = new Scanner(System.in);
	    //int x = in.nextInt();
	    Test7 hw = new Test7();

		hw.simpleif(2);
		
		hw.simpleif(1);
	}


	public void simpleif(int x){
		if(x==2){
			System.out.println(x);
		} else{
			System.out.println(x);
			System.out.println(x);
		}
	}

}