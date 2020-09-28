package com.test;

import com.example.Collection;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloCollection {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean_collection.xml");
        String[] beans = {
                "collect_all",
                "collect_list",
                "collect_map",
                "collect_set",
        };
        for (String item: beans) {
            System.out.println(
                    context.getBean(item, Collection.class)
            );
        }
    }
}
