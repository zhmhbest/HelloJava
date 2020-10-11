package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello");
        return "hello"; // 重定向到 hello.jsp
    }
}