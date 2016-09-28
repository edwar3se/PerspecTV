
public class toStringTest
{
    public static void main(String[] args)
    {
        Score test1 = new Score("Sydney", 45.0);
        System.out.println(test1.toString());
        
        Score test2 = new Score("Tyler", 31);
        System.out.println(test2.toString());
        
        Score test3 = new Score("Cassie", null);
        System.out.println(test3.toString());
        
        Score test4 = new Score("Cam");
        System.out.println(test4.toString());
                
    }
}
