/* 
    Nested class, loop, array and complex object
 */
import java.util.ArrayList;
public class Test6
{
    public static class someClass
    {
        static int b = 1;
        Integer func(int a)
        {
            System.out.println("hello");
            return a + b;
        }
    }
    
    public static void main (String[] args)
    {
        someClass anObject = new someClass();
        Object[] arr = new Object[10];
        arr[0] = anObject.func(3);
        arr[1] = new ArrayList<Integer>();
        ((ArrayList<Integer>)arr[1]).add ((Integer)arr[0]);
        for (int i = 0; i < 5; i++)
            System.out.println(arr[i]);
    }
}
