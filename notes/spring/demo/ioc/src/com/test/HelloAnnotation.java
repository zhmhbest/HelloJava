package com.test;

import com.example.annotation.FirstAnnotation;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloAnnotation {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_annotation.xml");

        System.out.println(
                context.getBean("firstAnnotation", FirstAnnotation.class)
        );

    }
}
