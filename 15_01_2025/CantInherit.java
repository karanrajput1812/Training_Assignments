sealed class Manager permits SalesManager, MarketingManager {
	int x = 10;

	public void abc() {
		System.out.println("Hello");
	}
}

final class Peun {
}

non-sealed class SalesManager extends Manager {
}

sealed class MarketingManager extends Manager permits DistributorManager {
}

final class DistributorManager extends MarketingManager {
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