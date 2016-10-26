
/* Code from http://beginnersbook.com/2013/03/polymorphism-in-java/ */


class Overload
{
    void demo (int a)
    {
       System.out.println ("a: " + a);
    }

    void demo (int a, int b)
    {
       System.out.println ("a and b: " + a + "," + b);
    }

    double demo(double a) {
       System.out.println("double a: " + a);
       return a*a;
    }
}

public class Test16
{
    public static void main (String args [])
    {
        Overload obj = new Overload();
        double result;
        obj.demo(10);
        obj.demo(10, 20);
        result = obj.demo(5.5);
        System.out.println("O/P : " + result);
    }
}

/*
   test1.Overload();
    flags: 
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return        
      LineNumberTable:
        line 6: 0

  void demo(int);
    flags: 
    Code:
      stack=3, locals=2, args_size=2
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: new           #3                  // class java/lang/StringBuilder
         6: dup           
         7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        10: ldc           #5                  // String a: 
        12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        15: iload_1       
        16: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        19: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        22: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        25: return        
      LineNumberTable:
        line 10: 0
        line 11: 25

  void demo(int, int);
    flags: 
    Code:
      stack=3, locals=3, args_size=3
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: new           #3                  // class java/lang/StringBuilder
         6: dup           
         7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        10: ldc           #10                 // String a and b: 
        12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        15: iload_1       
        16: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        19: ldc           #11                 // String ,
        21: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        24: iload_2       
        25: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        28: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        31: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        34: return        
      LineNumberTable:
        line 15: 0
        line 16: 34

  double demo(double);
    flags: 
    Code:
      stack=4, locals=3, args_size=2
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: new           #3                  // class java/lang/StringBuilder
         6: dup           
         7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        10: ldc           #12                 // String double a: 
        12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        15: dload_1       
        16: invokevirtual #13                 // Method java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        19: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        22: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        25: dload_1       
        26: dload_1       
        27: dmul          
        28: dreturn       
      LineNumberTable:
        line 19: 0
        line 20: 25

  public test1.Test16();
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return        
      LineNumberTable:
        line 24: 0

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=4, args_size=1
         0: new           #2                  // class test1/Overload
         3: dup           
         4: invokespecial #3                  // Method test1/Overload."<init>":()V
         7: astore_1      
         8: aload_1       
         9: bipush        10
        11: invokevirtual #4                  // Method test1/Overload.demo:(I)V
        14: aload_1       
        15: bipush        10
        17: bipush        20
        19: invokevirtual #5                  // Method test1/Overload.demo:(II)V
        22: aload_1       
        23: ldc2_w        #6                  // double 5.5d
        26: invokevirtual #8                  // Method test1/Overload.demo:(D)D
        29: dstore_2      
        30: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        33: new           #10                 // class java/lang/StringBuilder
        36: dup           
        37: invokespecial #11                 // Method java/lang/StringBuilder."<init>":()V
        40: ldc           #12                 // String O/P : 
        42: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        45: dload_2       
        46: invokevirtual #14                 // Method java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        49: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        52: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        55: return        
      LineNumberTable:
        line 28: 0
        line 30: 8
        line 31: 14
        line 32: 22
        line 33: 30
        line 34: 55
*/
