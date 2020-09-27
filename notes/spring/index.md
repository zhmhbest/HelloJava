<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Spring](../index.html)

[TOC]

<!-- https://repo.spring.io/release/org/springframework/spring/ -->

## 概论

### 控制反转（Inversion of Control, IOC）

把原本需要程序员自己创建和维护的Beans交由Spring管理。

### 面向切面编程（Aspect Oriented Programming, AOP）

交叉业务的编程问题即为面向切面编程。AOP的目标就是使交叉业务模块化。

## Hello

登录[Spring Initializr](https://start.spring.io/)下载一个项目模板。

[![Initializr](images/spring_initializr.png)](packages/hello-maven-java-2.2.10-springweb.7z)

```java
package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getHeader("User-Agent"));
        response.setHeader("MyHeader", "Get");
        return "Hello";
    }

    @PostMapping("/hello")
    public String postHello(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getHeader("User-Agent"));
        response.setHeader("MyHeader", "Post");
        return "Hello";
    }

}
```

```batch
mvn package
@FOR /F "usebackq" %f in (`DIR /B "target\*.jar"`) DO java -jar "target/%f"
REM 127.0.0.1:8080
```
