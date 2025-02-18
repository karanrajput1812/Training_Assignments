package com.example.model;

public final class Clerk extends Emp {
    public Clerk(Address address) {
        super(20000, "Clerk",address);
    }

    Clerk(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Clerk clerk = null;

    public static Clerk getClerk(Address address) {
        clerk = new Clerk(address);
        return clerk;
    }

    public static Clerk getClerk(int id, String name, int age, float salary, String designation) {
        if (clerk == null) {
            clerk = new Clerk(id, name, age, salary, designation);
        }
        return clerk;
    }

    @Override
    public void raiseSalary() {
        setSalary(getSalary() + 2000);
    }
}
