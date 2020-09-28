package com.test;

import com.example.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpecial {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_special.xml");
        String[] beans = {
                "user_null",
                "user_cdata",
                "user_outer",
                "user_inner"
        };
        for (String item: beans) {
            System.out.println(
                    context.getBean(item, Employee.class)
            );
        }
    }
}
