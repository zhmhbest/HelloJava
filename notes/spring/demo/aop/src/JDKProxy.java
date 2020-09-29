import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface UserInterface {
    void sayHello(String name);
    void sayGoodbye(String name);
}

class User implements UserInterface {
    @Override
    public void sayHello(String name) {
        System.out.printf("%s: Hello\n", name);
    }
    @Override
    public void sayGoodbye(String name) {
        System.out.printf("%s: Goodbye\n", name);
    }
}

public class JDKProxy {
    public static void main(String[] args) {
        // 增强User
        User obj = new User();
        UserInterface user = (UserInterface)Proxy.newProxyInstance(
            JDKProxy.class.getClassLoader(),
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
