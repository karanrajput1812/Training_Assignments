import java.io.*;
import java.sql.*;

class A
{
	public void abc() throws IOException, SQLException, UserException
	{
		int a = 50;
		for(int i=1; i<=20; i++)
		{
			System.out.println(i);
			int res = a/(a-i);
			if(i==150)
				throw new NullPointerException();
			if(i==120)
				throw new IOException();
			if(i==100)
				throw new SQLException();
			if(i==80)
				return;						
			if(i==60)
				System.exit(0);
			if(i==40)
				throw new UserException("When i is 4");
			if(i==200)
				throw new UserException("When i is 2");
		}
	}
	public void xyz() throws IOException, SQLException, UserException
	{
		try
		{
			abc();
		}
		catch(NullPointerException e)
		{
			System.out.println("** Handling NULL exception in xyz() method only**");
		}
	}
	public void atoz() throws IOException, SQLException, UserException
	{
		xyz();
	}
}
public class Exception_Throws_Demo 
{
	public static void main(String args[])
	{
		try 
		{
			A a1 = new A();
			a1.atoz();
		}
		catch(IOException | SQLException e)
		{
			System.out.println("IO/SQL Exception Handler");
		}
		catch(NullPointerException | ArithmeticException e)
		{
			System.out.println("NULLPointer / Arithmetic Exception Handler");
		}
		catch(UserException e)
		{
			System.out.println("Custom Exception Handler");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
			e.display();

		}
		catch(Exception e)
		{
			System.out.println("Default Exception Handler");
		}
		finally
		{
			System.out.println("Thank U..");
		}
		System.out.println("Program Continues...");
		try
		{
			int arr[] = new int[5];
			int res = arr[0]/0;
		}
		catch(ArithmeticException e)
		{
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
		}
		catch(RuntimeException e)
		{
		}
		catch(Exception e)
		{
		}
		finally
		{
		}
	}
}

class UserException extends Exception
{
	public UserException()
	{
		super(); //added by default
	}
	public UserException(String msg)
	{
		super(msg);
	}
	public void display()
	{
		System.out.println("My Custom Display Logic");

	}
}
