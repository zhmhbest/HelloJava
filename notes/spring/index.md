<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Spring](../index.html)

[TOC]

## Spring Framework

[![spring_architecture](images/spring_architecture.png)](https://repo1.maven.org/maven2/springframework/)

### 控制反转（Inversion of Control, IOC）

通过**XML配置**、**工厂模式**和**反射**等技术，把对象创建和调用过程交给Spring进行管理，以降低耦合度。

### 面向切面编程（Aspect Oriented Programming, AOP）

交叉业务的编程问题即为面向切面编程。AOP的目标就是使交叉业务模块化。

### IDEA配置

<!-- https://repo1.maven.org/maven2/com/alibaba/druid/ -->

添加[**Spring Framework**](https://repo.spring.io/release/org/springframework/spring/)和其它相关依赖的jar包路径到IDEA项目依赖。

![idea_add_spring](images/idea_add_spring.png)

## IOC

- 相关辅助对象
  - [`TestBeanConfig.java`](demo/src/ioc/TestBeanConfig.java)
  - [`Holder4String.java`](demo/src/ioc/Holder4String.java)
  - [`Holder4Object.java`](demo/src/ioc/Holder4Object.java)
  - [`Holder4Collection.java`](demo/src/ioc/Holder4Collection.java)
  - [`Holder4HS.java`](demo/src/ioc/Holder4HS.java)
  - annotation
    - [`User.java`](demo/src/ioc/annotation/User.java)
    - [`Name.java`](demo/src/ioc/annotation/Name.java)
    - [`Gender.java`](demo/src/ioc/annotation/Gender.java)
    - [`Age.java`](demo/src/ioc/annotation/Age.java)
- 属性注入
  - [`demoAttributeInjection.java`](demo/src/ioc/demoAttributeInjection.java)
  - [`demoAttributeInjection.xml`](demo/src/ioc/demoAttributeInjection.xml)
- 特殊符号、Object注入
  - [`demoSpecialInjection.java`](demo/src/ioc/demoSpecialInjection.java)
  - [`demoSpecialInjection.xml`](demo/src/ioc/demoSpecialInjection.xml)
- Collection、Map注入
  - [`demoCollectionInject.java`](demo/src/ioc/demoCollectionInject.java)
  - [`demoCollectionInject.xml`](demo/src/ioc/demoCollectionInject.xml)
- 自动注入
  - [`demoAutoInject.java`](demo/src/ioc/demoAutoInject.java)
  - [`demoAutoInject.xml`](demo/src/ioc/demoAutoInject.xml)
- 工厂方法
  - [`demoFactory.java`](demo/src/ioc/demoFactory.java)
  - [`demoFactory.xml`](demo/src/ioc/demoFactory.xml)
- 生命周期
  - [`demoLifeCycle.java`](demo/src/ioc/demoLifeCycle.java)
  - [`demoLifeCycle.xml`](demo/src/ioc/demoLifeCycle.xml)
- 注解方法
  - [`demoAnnotation.java`](demo/src/ioc/demoAnnotation.java)
  - [`demoAnnotation.xml`](demo/src/ioc/demoAnnotation.xml)
- 无配置注解方法
  - [`demoAnnotationNoXml.java`](demo/src/ioc/demoAnnotationNoXml.java)

## AOP

### 需要的依赖

@import "dependency.md"

### AOP实现方式

- 有接口使用[`JDK`](demo/src/aop/demoProxyJDK.java)动态代理；
- 没有接口使用[`CGLIB`](demo/src/aop/demoProxyCGLIB.java)动态代理（在子类中增强父类方法）。

在Spring中使用AspectJ进行AOP操作。

- [`UserInterface.java`](demo/src/aop/UserInterface.java)
- [`User.java`](demo/src/aop/User.java)
- [`UserProxy.java`](demo/src/aop/annotation/UserProxy.java)
- [`demo.java`](demo/src/aop/demoProxySpring.java)
- [`demo.xml`](demo/src/aop/demoProxySpring.xml)

### 基本概念

#### 连接点

可以被增强的方法。

#### 切入点

实际被增强的方法。

#### 通知

增强的逻辑部分，常见的有

- 前置通知
- 返回通知
- 环绕通知
- 最终通知
- 异常通知

#### 切面

把通知应用到切入点的过程。
