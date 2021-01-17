<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Nutz](../index.html)

[TOC]

## 创建项目

```bash
mvn archetype:generate "-DgroupId=org.example" "-DartifactId=nutzbook" "-DarchetypeArtifactId=maven-archetype-webapp" "-DinteractiveMode=false"
mkdir nutzbook/src/main/java
```

## POM配置

`pom.xml`

### 依赖

```xml
<!-- ... -->
<dependencies>
    <dependency>
        <groupId>org.nutz</groupId>
        <artifactId>nutz</artifactId>
        <version>1.r.60</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.40</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.0.26</version>
    </dependency>
    <dependency>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>tomcat-catalina</artifactId>
        <version>7.0.47</version>
    </dependency>
</dependencies>
<!-- ... -->
```

### 构建

```xml
<!-- ... -->
<build>
    <finalName>${project.artifactId}</finalName>
    <plugins>
        <plugin>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-maven-plugin</artifactId>
            <version>9.3.7.v20160115</version>
            <configuration>
                <scanIntervalSeconds>10</scanIntervalSeconds>
                <httpConnector>
                    <port>8080</port>
                </httpConnector>
                <webAppConfig>
                    <contextPath>/${project.build.finalName}</contextPath>
                </webAppConfig>
            </configuration>
        </plugin>
    </plugins>
</build>
<!-- ... -->
```

## Web配置

`web.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app ...><!-- 👈此处省略部分属性 -->

    <display-name>nutzbook</display-name>

    <!-- ↓↓↓ 由nutz处理所有请求 ↓↓↓ -->
    <filter>
        <filter-name>nutz</filter-name>
        <filter-class>org.nutz.mvc.NutFilter</filter-class>
        <init-param>
            <param-name>modules</param-name>
            <!-- 主模块（模块名称以实际情况为准） -->
            <param-value>org.example.nutzbook.MainModule</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>nutz</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!-- ↑↑↑ 由nutz处理所有请求 ↑↑↑ -->

    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
        <welcome-file>index.htm</welcome-file>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```

## 数据库配置

### 初始化数据库

```sql
-- nutzbook
-- nutz@localhost:nutz
CREATE DATABASE `nutzbook` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
CREATE USER 'nutz'@'localhost' IDENTIFIED BY 'nutz';
REVOKE ALL ON *.* FROM 'nutz'@'localhost';
GRANT ALL PRIVILEGES ON `nutzbook`.* TO 'nutz'@'localhost';
```

### 配置数据库

`src/main/resources/ioc/dao.js`

```js
var ioc = {
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["properties/"]
        }
    },
    dataSource : {
        factory : "$conf#make",
        args : ["com.alibaba.druid.pool.DruidDataSource", "db."],
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    }
};
```

`src/main/resources/properties/db.properties`

```bash
# mysql
db.url=jdbc:mysql://localhost:3306/nutzbook
db.username=nutz
db.password=nutz
# db.validationQuery=select 1
db.maxActive=100
db.testWhileIdle=true
db.filters=mergeStat
db.connectionProperties=druid.stat.slowSqlMillis=2000
# db.defaultAutoCommit=true
```

## 添加源码

### Java

```txt
src:
└─org
    └─example
        └─nutzbook
            │  Initializations.java
            │  MainModule.java
            │
            ├─controller
            │       *.java
            │
            ├─model
            │       *.java
            │
            └─page
                    *.java
```

#### 主模块

```java
package org.example.nutzbook;
import org.nutz.mvc.annotation.*;
@Ok("json:full")                                // 所有控制器默认返回JSON
@SetupBy(value = Initializations.class)         // 初始化模块
@IocBy(args = {                                 // 整个应用将采用何种方式构建 Ioc 容器
        "*js", "ioc/",                          // 加载配置
        "*anno", "org.example.nutzbook",        // 登记该包下所有带@IocBean的类
        "*tx",                                  // 事务拦截AOP
        "*async"                                // 异步执行AOP
})
@Modules(scanPackage = true)
public class MainModule { }
```

#### 初始化

```java
package org.example.nutzbook;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
public class Initializations implements Setup {
    public void init(NutConfig nc) {
        // 若不存在，创建所有数据表
        final String scanPackageName = Initializations.class.getPackage().getName() + ".model";
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, scanPackageName, false);

        // 添加一个默认用户
        if (dao.count(org.example.nutzbook.model.User.class) == 0) {
            org.example.nutzbook.model.User user = new org.example.nutzbook.model.User();
            user.setName("admin");
            user.setPassword("admin");
            dao.insert(user);
        }
    }
    public void destroy(NutConfig nc) {}
}
```

#### 模型

```java
package org.example.nutzbook.model;
import org.nutz.dao.entity.annotation.*;
@Table("tbl_user")
public class User {
    @Id
    private long id;
    @Name
    @Comment("用户名")
    private String name;
    @Column("password")
    @Comment("密码")
    private String password;
    // getter...
    // setter...
}
```

#### 接口

```java
package org.example.nutzbook.controller;
import org.example.nutzbook.model.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import javax.servlet.http.HttpSession;
@IocBean
@At("/user")
public class UserController {
    @Inject
    protected Dao dao;
    @At
    public Boolean login(
        @Param("name") String name,
        @Param("password") String password,
        HttpSession session
    ) {
        User user = dao.fetch(
            User.class,
            Cnd.where("name", "=", name).and("password", "=", password)
        );
        if (null != user) {
            session.setAttribute("name", user.getName());
            return true;
        }
        return false;
    }
    @At
    public String test(HttpSession session) {
        Object objName = session.getAttribute("name");
        if (null != objName) {
            return String.format("您好 %s", objName.toString());
        }
        return "您还未登录";
    }
}
```
