/*
[java] .limit stack 2
     [java] .limit locals 2
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] iinc 1 1
     [java] goto label1
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] iinc 1 1
     [java] goto label1
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] iinc 1 1
     [java] goto label1
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] iinc 1 1
     [java] goto label1
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] iinc 1 1
     [java] goto label1
     [java] 
     [java] iload_1
     [java] iconst_5
     [java] if_icmpgt label0
     [java] 
     [java] 6
     [java] getstatic java/lang/System/out Ljava/io/PrintStream;
     [java] iload_1
     [java] invokevirtual java/io/PrintStream/println(I)V
*/
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


class Test8{
	public static void main(String[] argv) throws Exception
	{
		//Scanner in = new Scanner(System.in);
	    //int x = in.nextInt();
	    Test8 hw = new Test8();
	    

		hw.one_while(1);
		

	}

	
	public void one_while(int x)
	{
		while(x<=5){
			x++;
		}
		System.out.println(x);
	}
}