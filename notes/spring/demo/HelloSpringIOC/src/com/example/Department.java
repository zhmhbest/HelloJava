package com.example;

public class Department {
    protected String name;
    public Department(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return String.format("Department{name='%s'}", this.name);
    }
}
