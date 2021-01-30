package aop;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.Enhancer;

public class demoProxyCGLIB {
    static class UserInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(
                Object obj, Method method, Object[] params, MethodProxy proxy
        ) throws Throwable {
            String name = method.getName();
            System.out.printf("====Before : %s\n", name);
            Object result = proxy.invokeSuper(obj, params);
            System.out.printf("====After  : %s\n", name);
            return result;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new UserInterceptor());
        User user = (User)enhancer.create();
        System.out.println(user);
        user.sayHello("Java");
        user.sayGoodbye("Python");
    }
}
