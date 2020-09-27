package com.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello {
    public void say() {
        System.out.println("Hello!");
    }
    public static void main(String[] args) {
        // 在加载配置文件时创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Hello hello = context.getBean("hello", Hello.class);
        hello.say();
    }
}
