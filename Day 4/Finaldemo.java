class A
{
	final int x = 10;
	final public void abc()	
	{
		System.out.println("HI");
	}
}

class B extends A
{	
	/*
	public void abc()	
	{
		System.out.println("HELLO");
	}
	*/
}	
public class Finaldemo
	
{
	public static void main(String args[])
	{
		A a1 = new A();
		System.out.println(a1.x);
		System.out.println(a1.x + 10);
		a1.abc();
		B b1 = new B();
		b1.abc();
	}

}