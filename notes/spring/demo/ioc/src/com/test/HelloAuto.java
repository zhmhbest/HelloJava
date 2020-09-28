package com.test;

import com.example.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloAuto {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_auto.xml");

        System.out.println(
                context.getBean("user_by1", Employee.class)
        );
        System.out.println(
                context.getBean("user_by2", Employee.class)
        );
    }
}
