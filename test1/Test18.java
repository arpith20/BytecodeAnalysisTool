
/* Code from  http://beginnersbook.com/2013/05/inner-class/ */

public class Test18
{
   private int x= 1;
   public void innerInstance()
   {
       MyInnerClassDemo inner = new MyInnerClassDemo();
       inner.seeOuter(5);
   }
   public static void main(String args[]){
       Test18 obj = new Test18();
       obj.innerInstance();
   }
   // inner class definition
   class MyInnerClassDemo {
       public void seeOuter (int y) {
          if(y > 0) 
             System.out.println("Outer Value of x is :" + x);
          else 
	     y++; 
       }
   } // close inner class defini
}


/* 
public void innerInstance();
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=2, args_size=1
         0: new           #3                  // class test1/Test18$MyInnerClassDemo
         3: dup           
         4: aload_0       
         5: invokespecial #4                  // Method test1/Test18$MyInnerClassDemo."<init>":(Ltest1/Test18;)V
         8: astore_1      
         9: aload_1       
        10: iconst_5      
        11: invokevirtual #5                  // Method test1/Test18$MyInnerClassDemo.seeOuter:(I)V
        14: return        
      LineNumberTable:
        line 10: 0
        line 11: 9
        line 12: 14

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #6                  // class test1/Test18
         3: dup           
         4: invokespecial #7                  // Method "<init>":()V
         7: astore_1      
         8: aload_1       
         9: invokevirtual #8                  // Method innerInstance:()V
        12: return        
      LineNumberTable:
        line 14: 0
        line 15: 8
        line 16: 12

  static int access$000(test1.Test18);
    flags: ACC_STATIC, ACC_SYNTHETIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: getfield      #1                  // Field x:I
         4: ireturn       
      LineNumberTable:
        line 5: 0


  test1.Test18$MyInnerClassDemo(test1.Test18);
    flags: 
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0       
         1: aload_1       
         2: putfield      #1                  // Field this$0:Ltest1/Test18;
         5: aload_0       
         6: invokespecial #2                  // Method java/lang/Object."<init>":()V
         9: return        
      LineNumberTable:
        line 18: 0

  public void seeOuter(int);
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=2, args_size=2
         0: iload_1       
         1: ifle          38
         4: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: new           #4                  // class java/lang/StringBuilder
        10: dup           
        11: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
        14: ldc           #6                  // String Outer Value of x is :
        16: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        19: aload_0       
        20: getfield      #1                  // Field this$0:Ltest1/Test18;
        23: invokestatic  #8                  // Method test1/Test18.access$000:(Ltest1/Test18;)I
        26: invokevirtual #9                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        29: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        32: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        35: goto          41
        38: iinc          1, 1
        41: return        
      LineNumberTable:
        line 20: 0
        line 21: 4
        line 23: 38
        line 24: 41
      StackMapTable: number_of_entries = 2
           frame_type = 38 same 
           frame_type = 2  same 
*/
