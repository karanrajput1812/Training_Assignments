import java.io.*;
import java.sql.*;


public class ExceptionDemo 
{
	public static void main(String args[])
	{
		int a = 50;
		try
		{
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



// Doesn't Required the catch program in the main class or wherever try block with user defined exception throw statement
/*
class UserException extends RuntimeException
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
*/