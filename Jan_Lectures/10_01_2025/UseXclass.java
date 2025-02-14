import com.X;

class Y extends X
{
	public void display()
	{
		System.out.println(a);
		demo();
	}
}

class UseXclass 
{
	public static void main(String args[])
	{
		Y y1 = new Y();
		y1.display();

	}
}