package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class demoProxyJDK {
    public static void main(String[] args) {
        // 增强User
        User obj = new User();
        UserInterface user = (UserInterface)Proxy.newProxyInstance(
            demoProxyJDK.class.getClassLoader(),
            new Class[]{UserInterface.class},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    String name = method.getName();
                    Object ret = null;
                    System.out.printf("====Before : %s\n", name);
                    ret = method.invoke(obj, args);
                    System.out.printf("====After  : %s\n", name);
                    return ret;
                }
            }
        );
        user.sayHello("Java");
        user.sayGoodbye("Python");
    }
}
