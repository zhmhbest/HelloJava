package com.example;

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    protected String name;
    protected Integer age;
    protected Boolean gender;

    public User() {
        this.name = "无名氏";
        this.age = 0;
        this.gender = false;
    }

    public User(String name, Integer age, Boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getInformation() {
        return String.format(
            "%s %d %s",
            this.name,
            this.age,
            this.gender ? "男" : "女"
        );
    }

    public void introduceSelf() {
        System.out.println(this.getInformation());
    }
}
