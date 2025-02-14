package com;

public class Calculator {
//	Calculator()
//	{
//		System.out.println("<======== Calculator() Object Created .....");
//	}
	public int add(int a, int b) 
	{
		return a+b;
	}
	public int sub(int a, int b) 
	{
		return a-b;
	}
	public int mul(int a, int b) 
	{
		return a*b;
	}
	public int div(int a, int b) throws ArithmeticException
	{
		if(b!=0)
			return a/b;
		else
			throw new ArithmeticException();
	}
	public int mod(int a, int b)
	{
		return a%b;
	}
}
