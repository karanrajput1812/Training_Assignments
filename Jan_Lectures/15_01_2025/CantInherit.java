sealed class Manager permits SalesManager, MarketingManager {
	int x = 10;

	public void abc() {
		System.out.println("Hello");
	}
}

final class Peun {
}

non-sealed class SalesManager extends Manager {    // extends the class from Manager And allows other class to inherit this class
}

sealed class MarketingManager extends Manager permits DistributorManager { // extends the class from Manager And permits other class to inherit this class whichever in permission
}

final class DistributorManager extends MarketingManager {	// extends the class from Manager And not allows other class to inherit this class
}

class GlobalManager extends SalesManager {

}

public class CantInherit {
	public static void main(String args[]) {
		GlobalManager m1 = new GlobalManager();
		// System.out.println(b1.x);
		m1.abc();
	}
}