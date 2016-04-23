public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello, World!");
        try
        {
            System.in.read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
