package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ioc.annotation")
class SpringConfig { }

public class demoAnnotationNoXml {
    public static void main(String[] args) {
        TestBeanConfig.loadConfiguration(SpringConfig.class);
    }
}
