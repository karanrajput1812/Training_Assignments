package assignment;

class Calculator
{
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
}
public class CalculatorMain
{
	public static void main(String args[])
	{
		System.out.println("Calculator Operations......");
 
		Calculator c1 = new Calculator();
		System.out.println("Addition : "+ c1.add(10, 20));
		System.out.println("Substract : "+ c1.sub(10, 20));
		System.out.println("Multiply : "+ c1.mul(10, 20));
	}
}
