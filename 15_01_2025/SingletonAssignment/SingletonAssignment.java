sealed class A permits B
{
	static private A a1 = null;
	
	protected A()
	{
		System.out.println("Object A created");
	}
	public static A getObject()
	{
		try
		{
			if(a1 == null)
				a1 = new A();
			else
				throw new UserException("One Instace of A is already created");
		}
		catch (UserException e)
		{
			System.out.println("Reason: " + e.getMessage());
		}
		return a1;
	}
}
final class B extends A
{
	static private B b1 = null;
	private B()
	{
		System.out.println("Object B created");
	}
	public static B getObject()
	{
		try
		{
			if(b1 == null)
				b1 = new B();
			else
				throw new UserException("One Instace of B is already created");
		}
		catch (UserException e)
		{
			System.out.println("Reason: " + e.getMessage());
		}
		return b1;
			
	}

}

public class SingletonAssignment
{
	public static void main(String args[])
	{
		A a1 = A.getObject();
		A a2 = A.getObject();
		B b1 = B.getObject();
		B b2 = B.getObject();

	}
}

class UserException extends RuntimeException
{
	public UserException()
	{
		super(); 	
	}
	public UserException(String msg)
	{
		super(msg);
	}
}
