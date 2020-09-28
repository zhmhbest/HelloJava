package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloPrototype {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_prototype.xml");

        // 多实例
        Object obj1 = context.getBean("the_prototype", Object.class);
        Object obj2 = context.getBean("the_prototype", Object.class);
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj1 == obj2);
    }
}
