class Demo {
    {
        try{
        System.out.println("Demo....... 1");
        System.out.println("Demo....... 2");
        System.out.println("Demo....... 3");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    Demo()
    {
        System.out.println("From Demo() Constructor");
    }
    static
    {
        System.out.println("From static 1: ->");
    }
    Demo (int i)
    {
        System.out.println("From Demo(int) Constructor");

    }
    {
        System.out.println("Demo....... 2");
    }
    Demo(String s)
    {
        System.out.println("From Demo(String) Constructor");
    }
    {
        System.out.println("Demo....... 3");
    }
    static
    {
        System.out.println("From static 2: ->");
    }
    public static void main(String[] args) {
        System.out.println("From main method :)");
        Demo d1 = new Demo();
        Demo d2 = new Demo(1);
        Demo d3 = new Demo("Karan");
    }
}



// Output:
// From static 1: ->
// From static 2: ->
// From main method :)
// Demo....... 1
// Demo....... 2
// Demo....... 3
// From Demo() Constructor
// Demo....... 1
// Demo....... 2
// Demo....... 3
// From Demo(int) Constructor
// Demo....... 1
// Demo....... 2
// Demo....... 3
// From Demo(String) Constructor
