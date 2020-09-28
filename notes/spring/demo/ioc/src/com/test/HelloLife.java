package com.test;

import com.example.Life;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloLife {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_life.xml");
        // 生命周期
        System.out.println(
                context.getBean("the_obj", Object.class)
        );
        System.out.println(
                context.getBean("the_life", Life.class)
        );
        context.close();
    }
}
