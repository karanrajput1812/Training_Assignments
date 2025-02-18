package com.example.model;

public final class Manager extends Emp {
    public Manager( Address address) {
        super(100000, "Manager", address);
    }

    Manager(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Manager manager = null;

    public static Manager getManager( Address address) {
        manager = new Manager(address);
        return manager;
    }

    public static Manager getManager(int id, String name, int age, float salary, String designation) {
        if (manager == null) {
            manager = new Manager(id, name, age, salary, designation);
        }
        return manager;
    }

    @Override
    public void raiseSalary() {
        setSalary(getSalary() + 15000);
    }
}

