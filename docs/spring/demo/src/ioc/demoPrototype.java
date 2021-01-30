package ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class demoPrototype {
    public static void main(String[] args) {
        String configLocation = demoPrototype.class.getName().replace(".", "/") + ".xml";
        System.out.printf("configLocation = %s\n", configLocation);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        Object obj1 = context.getBean("prototype_object", Object.class);
        Object obj2 = context.getBean("prototype_object", Object.class);
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj1 == obj2);
    }
}
