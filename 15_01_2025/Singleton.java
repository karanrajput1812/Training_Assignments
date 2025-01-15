//	Early Instance singleton type
/*final class Principal
{
	static private final Principal p1 = new Principal();
	private Principal()
	{
		System.out.println("Principal() instance is created");
	}
	public static Principal getPrincipal()
	{
		return p1;
	}
}
*/

//	Lazy Instance singleton type
final class Principal
{
	static private Principal p1 = null;
	private Principal()
	{
		System.out.println("Principal() instance is created");
	}
	public static Principal getPrincipal()
	{
		if(p1 == null)
			p1 = new Principal();
		return p1;
	}
}


public class Singleton
{
	static
	{
		try
		{
			Class.forName("Principal");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void main(String args[])
	{
		Principal p1 = Principal.getPrincipal();
		Principal p2 = Principal.getPrincipal();
		Principal p3 = Principal.getPrincipal();

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}
}