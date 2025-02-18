package com.example.model;

public final class CEO extends Emp {
    private static CEO ceo = null;
    protected static boolean isCEO = false;

    public CEO(Address address) {
        super(2000000, "CEO", address);
    }

    public static void setCEO(boolean isCEO) {
		CEO.isCEO = isCEO;
	}
    
    public static boolean isCEO() {
		return isCEO;
	}

    public static void resetCEO() {
        ceo = null;
        isCEO = false;
    }

    @Override
    public void raiseSalary() {
        setSalary(getSalary() + 150000);
    }
}