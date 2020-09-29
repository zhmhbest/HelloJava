package ioc;
/*
    import org.springframework.beans.factory.BeanFactory;   // 在使用时创建对象
    import org.springframework.context.ApplicationContext;  // 在加载配置时创建对象
    import org.springframework.context.support.ClassPathXmlApplicationContext;  // 指定基于src的相对路径
    import org.springframework.context.support.FileSystemXmlApplicationContext; // 指定全路径
*/
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanConfig {
    public static void printBeans(ApplicationContext context) {
        for (String beanName: context.getBeanDefinitionNames()) {
            Object obj = context.getBean(beanName);
            System.out.printf("%s = %s\n", beanName, obj);
        }
    }

    /**
     * 注解方式配置
     */
    public static void loadConfiguration(Class obj) {
        ApplicationContext context = new AnnotationConfigApplicationContext(obj);
        printBeans(context);
    }

    /**
     * XML配置
     */
    public static void loadXML(Class obj) {
        String configLocation = obj.getName().replace(".", "/") + ".xml";
        System.out.printf("configLocation = %s\n", configLocation);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        printBeans(context);
    }
}
