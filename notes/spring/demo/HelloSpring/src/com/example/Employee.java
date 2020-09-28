package com.example;

public class Employee {
    protected String name;
    protected Integer age;
    protected Boolean gender;
    protected Department department;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee() {
        this.name = "无名氏";
        this.age = 0;
        this.gender = false;
        this.department = new Department("");
    }

    public Employee(String name, Integer age, Boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = new Department("");
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{name='%s', age=%d, gender=%b, department=%s}",
                this.name, this.age, this.gender, this.department
        );
    }
}
