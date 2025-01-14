class A implements AutoCloseable	// Required For the TryWithResources
{
	A()
	{
		System.out.println("**Allocating resources for A class object**");
	}
	public void process()
	{
		System.out.println("Performing the required processing on A object");

	}
	public void close()	// Compulsory for AutoCloseable
	{
		System.out.println("***Releasing resources for A class object***");
	}
}

class B implements AutoCloseable	// Required For the TryWithResources
{
	B()
	{
		System.out.println("**Allocating resources for B class object**");
	}
	public void process()
	{
		System.out.println("Performing the required processing on B object");

	}
	public void close()	// Compulsory for AutoCloseable
	{
		System.out.println("***Releasing resources for B class object***");
	}
}

public class TryWithResources {
    public static void main(String[] args)
    {
		// Order Of execution depends on the instance creation order
		try(B b1 = new B(); A a1 = new A();)
		{
			a1.process();
			if(true)
				throw new NullPointerException();
			b1.process(); // Not executed due to the above exception
		}
		// System.out.println(a1);
    }
}
