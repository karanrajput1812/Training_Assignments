interface I 
{
}

class A implements I
{
	public void doSomeOperation() 
	{
		if(this instanceof I)
			System.out.println("Yes now you can do this activity")
		else
			throw new NullPointerException();
	}

}

class Markup
{
	public static void main(String args[])
	{
		A a1 = new A();
		a1.doSomeOperation();
	}


	
}
