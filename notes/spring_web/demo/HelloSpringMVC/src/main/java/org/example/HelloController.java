package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
public class HelloController {
    @RequestMapping(path = "/hello")
    public String demo_hello(Model model) {
        System.out.println("Hello");

        // 在接下来的页面中可以使用的引用
        model.addAttribute("hi", new String("This is 'hello' !"));

        // 重定向到 WEB-INF/pages/hello.jsp
        return "hello";
    }

    @RequestMapping(path = "/void")
    public void demo_void(Model model) {
        System.out.println("Void");

        model.addAttribute("hi", new String("This is 'void' !"));

        // 默认重定向到 WEB-INF/pages/void.jsp
    }

    @RequestMapping(path = "/rr")
    public void demo_rr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("RR");
        // 读取请求Header
        StringBuilder buffer = new StringBuilder(1024);
        Enumeration<String> keys = request.getHeaderNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            buffer.append(String.format("%s=%s\n", key, request.getHeader(key)));
        }
        // 返回字符串
        PrintWriter out = response.getWriter();
        out.println(buffer);
        out.close();
    }
}