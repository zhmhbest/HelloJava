package example.nutzbook;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={
    "*js", "ioc/",                      // 加载IOC配置
    "*anno", "example.nutzbook.bean",   // 登记包下所有带@IocBean的类
    "*tx",                              // 事务拦截AOP
    "*async"                            // 异步执行AOP
})
@Modules(scanPackage=true)
public class MainModule {
}
