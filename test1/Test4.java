/* Tail Recursion  */

public class Test4
{
    static void tailRecursion(int x)
    {
        if (x==0)
            return;
        System.out.println("x = "+x);
        tailRecursion(x-1);
    }
    
    public static void main (String[] args)
    {
        tailRecursion (10);
    }
}
