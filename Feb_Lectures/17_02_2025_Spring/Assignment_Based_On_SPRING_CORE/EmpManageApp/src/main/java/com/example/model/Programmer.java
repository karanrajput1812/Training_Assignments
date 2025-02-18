package com.example.model;

public final class Programmer extends Emp {
    public Programmer( Address address) {
        super(30000, "Programmer", address);
    }

    Programmer(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    private static Programmer programmer = null;

    public static Programmer getProgrammer( Address address) {
        programmer = new Programmer( address);
        return programmer;
    }

    public static Programmer getProgrammer(int id, String name, int age, float salary, String designation) {
        if (programmer == null) {
            programmer = new Programmer(id, name, age, salary, designation);
        }
        return programmer;
    }

    @Override
    public void raiseSalary() {
        setSalary(getSalary() + 5000);
    }
}