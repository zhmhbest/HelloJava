## Servlet

### Xml模式

`src/com.example.HelloServlet`

```java
package com.example;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;


// 尽量不要在Servlet中定义成员变量
public class HelloServlet implements Servlet {

    /**
     * （生命周期）仅在Servlet被创建时执行
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException { }

    /**
     * （生命周期）每次Servlet被访问时执行
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("Hello Servlet");
        writer.close();
    }

    /**
     * （生命周期）仅在Servlet被销毁时（服务器正常关闭时）执行
     */
    @Override
    public void destroy() { }

    /**
     * 获取Servlet的Config对象
     */
    @Override
    public ServletConfig getServletConfig() { return null; }

    /**
     * 获取Servlet的信息
     */
    @Override
    public String getServletInfo() { return null; }
}
```

`web/WEB-INF/web.xml`

```xml
    <!-- ... -->
    <servlet>
        <servlet-name>NameOf_HelloServlet</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
        <!--
          <load-on-startup>{value}</load-on-startup>
          【指定Servlet何时加载】
            {value} < 0: 第一次被访问时创建（默认）
            {value} ≥ 0: 服务器启动时创建
        -->
    </servlet>
    <servlet-mapping>
        <servlet-name>NameOf_HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

### Annotation模式

`src/com.example.HelloServlet`

```java
package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = {"/hello"})
@WebServlet("/hello")
public class HelloServlet implements Servlet {
    @Override public void init(ServletConfig servletConfig) throws ServletException { }
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("Hello Servlet");
        writer.close();
    }
    @Override public void destroy() { }
    @Override public ServletConfig getServletConfig() { return null; }
    @Override public String getServletInfo() { return null; }
}
```
