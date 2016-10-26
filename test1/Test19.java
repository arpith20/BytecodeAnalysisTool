
public class Test19
{
   public static void main(String args[]) {
      for(int i = 0; i < 10; i++) {
         if(i%2 == 0) 
           foo(); 
         else 
           System.out.println("Hello");  
      }
   }

   public static void foo() {
      int x = 7; 
   }
}

/*  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0      
         1: istore_1      
         2: iload_1       
         3: bipush        10
         5: if_icmpge     34
         8: iload_1       
         9: iconst_2      
        10: irem          
        11: ifne          20
        14: invokestatic  #2                  // Method foo:()V
        17: goto          28
        20: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        23: ldc           #4                  // String Hello
        25: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        28: iinc          1, 1
        31: goto          2
        34: return        
      LineNumberTable:
        line 7: 0
        line 8: 8
        line 9: 14
        line 11: 20
        line 7: 28
        line 13: 34
      StackMapTable: number_of_entries = 4
           frame_type = 252  append 
             offset_delta = 2
        locals = [ int ]
           frame_type = 17 same 
           frame_type = 7  same 
           frame_type = 250 chop 
          offset_delta = 5


  public static void foo();
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=1, args_size=0
         0: bipush        7
         2: istore_0      
         3: return        
      LineNumberTable:
        line 16: 0
        line 17: 3
*/
