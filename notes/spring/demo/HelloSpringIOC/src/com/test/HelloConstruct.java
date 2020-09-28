package com.test;

import com.example.Employee;
import org.springframework.beans.factory.BeanFactory;   // 在使用时创建对象
import org.springframework.context.ApplicationContext;  // 在加载配置时创建对象
import org.springframework.context.support.ClassPathXmlApplicationContext;  // 指定基于src的相对路径
import org.springframework.context.support.FileSystemXmlApplicationContext; // 指定全路径

public class HelloConstruct {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_construct.xml");
        String[] beans = {
                "user_first",
                "user_property",
                "user_constructor",
                "user_p"
        };
        for (String item: beans) {
            System.out.println(
                    context.getBean(item, Employee.class)
            );
        }
    }
}
