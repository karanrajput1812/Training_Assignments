sealed class A permits B {
	static private A a1 = null;
	protected A() {
		if (this instanceof B) {
			System.out.println("B instance created...........");
		}
		else if(this instanceof A && a1==null) {
			System.out.println("A instance created...........");
			a1 = this;
		}
		else {
			throw new InstanceAlreadyExists("One Instace of A is already created...........");
		}
	}

	public static A getObject() {
		try {
			if (a1 == null)
				a1 = new A();
			else
				throw new InstanceAlreadyExists("One Instace of A is already created");
		} catch (InstanceAlreadyExists e) {
			System.out.println("Reason: " + e.getMessage());
		}
		return a1;
	}
}
final class B extends A {
	static private B b1 = null;

	private B() {
	}

	public static B getObject() {
		try {
			if (b1 == null)
				b1 = new B();
			else
				throw new InstanceAlreadyExists("One Instace of B is already created");
		} catch (InstanceAlreadyExists e) {
			System.out.println("Reason: " + e.getMessage());
		}
		return b1;
	}
}

public class SingletonAssignment {
	public static void main(String args[]) {
		A a1 = A.getObject();
		A a2 = A.getObject();
		B b1 = B.getObject();
		B b2 = B.getObject();
		A a3 = new A();
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(b1);
		System.out.println(b2);

	}
}

class InstanceAlreadyExists extends RuntimeException {
	public InstanceAlreadyExists() {
		super();
	}

	public InstanceAlreadyExists(String msg) {
		super(msg);
	}
}