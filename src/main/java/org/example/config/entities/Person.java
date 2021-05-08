package org.example.config.entities;

import lombok.Data;


@Data
public class Person {
    private int id;
    private String name;
    private int gender;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
