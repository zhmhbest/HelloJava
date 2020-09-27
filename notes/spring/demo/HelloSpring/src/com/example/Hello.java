package com.example;

import org.springframework.beans.factory.BeanFactory;   // 在使用时创建对象
import org.springframework.context.ApplicationContext;  // 在加载配置时创建对象
import org.springframework.context.support.ClassPathXmlApplicationContext;  // 指定基于src的相对路径
import org.springframework.context.support.FileSystemXmlApplicationContext; // 指定全路径

public class Hello {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        User user = null;
        String[] beans = {
                "user_first",
                "user_property",
                "user_constructor",
                "user_p",
                "user_null",
                "user_cdata",
                "user_outer_object",
                "user_inner_object",
        };
        for (String item: beans) {
            user = context.getBean(item, User.class);
            user.introduceSelf();
        }

        Collection collect = context.getBean("collect", Collection.class);
        for(String item: collect.getArr()) {
            System.out.printf("%s ", item);
        }
        System.out.print('\n');
        System.out.println(collect.getList());
        System.out.println(collect.getMap());
        System.out.println(collect.getSet());
    }
}
