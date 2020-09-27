package com.example;

import org.springframework.beans.factory.BeanFactory;   // 在使用时创建对象
import org.springframework.context.ApplicationContext;  // 在加载配置时创建对象
import org.springframework.context.support.ClassPathXmlApplicationContext;  // 指定基于src的相对路径
import org.springframework.context.support.FileSystemXmlApplicationContext; // 指定全路径

public class Hello {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
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

        Collection collect = context.getBean("collect_all", Collection.class);
        for(String item: collect.getArr()) {
            System.out.printf("%s ", item);
        } System.out.print('\n');
        for(Object item: collect.getObj()) {
            System.out.printf("%s ", item);
        } System.out.print('\n');
        System.out.println(collect.getList());
        System.out.println(collect.getMap());
        System.out.println(collect.getSet());
        System.out.println(
                context.getBean("collect_list", Collection.class).getList()
        );

        // 工厂Bean
        System.out.println(
                context.getBean("factory", String.class)
        );

        // 多实例
        System.out.println(
                context.getBean("prototype", Object.class)
                ==
                context.getBean("prototype", Object.class)
        );

        // 生命周期
        System.out.println(
                context.getBean("life", Life.class)
        );

        context.close();
    }
}
