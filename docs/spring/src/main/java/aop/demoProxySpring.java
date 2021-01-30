package aop;

import ioc.TestBeanConfig;
import org.springframework.context.ApplicationContext;
import java.util.Arrays;

public class demoProxySpring {
    public static void main(String[] args) {
        ApplicationContext context = TestBeanConfig.getXML(demoProxySpring.class);
        //System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        User user = context.getBean("user", User.class);
        user.hi();
        user.sayHello("Java");
        user.sayGoodbye("Python");
    }
}