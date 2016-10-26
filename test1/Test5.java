/* Recursion  */

public class Test5
{
    static int recursion (int x)
    {
        if (x==0)
            return 0;
        int b = x*x;
        int a = recursion(x-1);
        return a+b;
    }
    public static void main (String[] args)
    {
        Integer a = 3, b = 4;
        Integer num = a+b;
        recursion (num);
        System.out.println(num);
    }
}
