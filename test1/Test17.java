
/* Code from http://beginnersbook.com/2013/04/nested-try-catch/ */

public class Test17
{
  public static void main(String args[]){
     //Parent try block
     try{
    	//Child try block1
         try{
            System.out.println("Inside block1");
            int b =45/0;
            System.out.println(b);
         }
         catch(ArithmeticException e1){
            System.out.println("Exception: e1");
         }
         //Child try block2
         try{
            System.out.println("Inside block2");
            int b =45/0;
            System.out.println(b);
         }
         catch(ArrayIndexOutOfBoundsException e2){
            System.out.println("Exception: e2");
         }
        System.out.println("Just other statement");
    }
    catch(ArithmeticException e3){
    	 System.out.println("Arithmetic Exception");
         System.out.println("Inside parent try catch block");
    }
    catch(ArrayIndexOutOfBoundsException e4){
    	System.out.println("ArrayIndexOutOfBoundsException");
         System.out.println("Inside parent try catch block");
    }
    catch(Exception e5){
    	System.out.println("Exception");
         System.out.println("Inside parent try catch block");
     }
     System.out.println("Next statement..");
  }
}

/*  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String Inside block1
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: bipush        45
        10: iconst_0      
        11: idiv          
        12: istore_1      
        13: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        16: iload_1       
        17: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        20: goto          32
        23: astore_1      
        24: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        27: ldc           #7                  // String Exception: e1
        29: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        32: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        35: ldc           #8                  // String Inside block2
        37: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        40: bipush        45
        42: iconst_0      
        43: idiv          
        44: istore_1      
        45: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        48: iload_1       
        49: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
        52: goto          64
        55: astore_1      
        56: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        59: ldc           #10                 // String Exception: e2
        61: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        64: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        67: ldc           #11                 // String Just other statement
        69: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        72: goto          132
        75: astore_1      
        76: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        79: ldc           #12                 // String Arithmetic Exception
        81: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        84: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        87: ldc           #13                 // String Inside parent try catch block
        89: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        92: goto          132
        95: astore_1      
        96: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        99: ldc           #14                 // String ArrayIndexOutOfBoundsException
       101: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       104: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       107: ldc           #13                 // String Inside parent try catch block
       109: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       112: goto          132
       115: astore_1      
       116: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       119: ldc           #16                 // String Exception
       121: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       124: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       127: ldc           #13                 // String Inside parent try catch block
       129: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       132: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       135: ldc           #17                 // String Next statement..
       137: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       140: return        
      Exception table:
         from    to  target type
             0    20    23   Class java/lang/ArithmeticException
            32    52    55   Class java/lang/ArrayIndexOutOfBoundsException
             0    72    75   Class java/lang/ArithmeticException
             0    72    95   Class java/lang/ArrayIndexOutOfBoundsException
             0    72   115   Class java/lang/Exception
      LineNumberTable:
        line 13: 0
        line 14: 8
        line 15: 13
        line 19: 20
        line 17: 23
        line 18: 24
        line 22: 32
        line 23: 40
        line 24: 45
        line 28: 52
        line 26: 55
        line 27: 56
        line 29: 64
        line 42: 72
        line 31: 75
        line 32: 76
        line 33: 84
        line 42: 92
        line 35: 95
        line 36: 96
        line 37: 104
        line 42: 112
        line 39: 115
        line 40: 116
        line 41: 124
        line 43: 132
        line 44: 140
      StackMapTable: number_of_entries = 8
           frame_type = 87  same_locals_1_stack_item 
          stack = [ class java/lang/ArithmeticException ]
           frame_type = 8  same 
           frame_type = 86  same_locals_1_stack_item 
          stack = [ class java/lang/ArrayIndexOutOfBoundsException ]
           frame_type = 8  same 
           frame_type = 74 same_locals_1_stack_item 
          stack = [ class java/lang/ArithmeticException ]
           frame_type = 83 same_locals_1_stack_item 
          stack = [ class java/lang/ArrayIndexOutOfBoundsException ]
           frame_type = 83 same_locals_1_stack_item 
          stack = [ class java/lang/Exception ]
           frame_type = 16 same 
*/
