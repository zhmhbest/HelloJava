package com.test;

import com.example.annotation.FirstAnnotation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.annotation")
class SpringConfig { }

public class HelloSpringConfig {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(
                context.getBean("firstAnnotation", FirstAnnotation.class)
        );
    }
}
