package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloFactory {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_factory.xml");

        // 工厂Bean的返回对象与工厂对象不一致
        // Error: Factory.class
        String factory = context.getBean("the_factory", String.class);
        System.out.println(factory);
    }
}
