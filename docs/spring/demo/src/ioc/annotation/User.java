package ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/*
 * @Component
 * @Service
 * @Controller
 * @Repository
 */

//@Component(value = "firstAnnotation")
@Component  // value默认为首字母小写的类名
public class User {
    // 根据类型自动注入
    @Autowired
    protected Name name;

    // 根据名称进行注入（需要配合@Autowired一起使用）
    @Autowired
    @Qualifier(value = "gender")
    protected Gender gender;

    //@Resource(name = "age")   // 根据名称注入
    @Resource                   // 根据类型注入
    protected Age age;

    @Value(value = "Saharan")
    protected String hometown;

    @Override
    public String toString() {
        return String.format(
                "FirstAnnotation{name=%s, gender=%s, age=%s, home=%s}",
                this.name, this.gender, this.age, this.hometown
        );
    }
}
