package aop.annotation;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect     // 生成代理对象
@Order(1)   // 代理对象优先级，越低越早执行
public class UserProxy {
    // execution([权限] [返回类型] [类路径] [方法名称] (参数列表))
    @Pointcut(value = "execution(* aop.User.hi(..))")
    public void extractPoint() {}

    @Before(value = "extractPoint()")
    public void before() {
        System.out.print("\tBefore\n");
    }

    @After(value = "extractPoint()")
    public void after() {
        System.out.print("\tAfter\n");
    }

    @Around(value = "extractPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.print("\tBefore around\n");
        Object ret = point.proceed();
        System.out.print("\tAfter around\n");
        return ret;
    }

    @AfterReturning(value = "extractPoint()")
    public void afterReturning() {
        System.out.print("\tafterReturning\n");
    }

    // 异常通知
    @AfterThrowing(value = "extractPoint()")
    public void afterThrowing() {
        System.out.print("\tafterThrowing\n");
    }
}
