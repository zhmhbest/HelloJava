<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Tomcat](./index.html)

[TOC]

## Tomcat目录结构

- apache-tomcat
  - bin
    - `startup.sh`
    - `shutdown.sh`
  - conf
    - Catalina
      - localhost
        - `?.xml`
    - `server.xml`
    - `tomcat-users.xml`
  - lib
  - logs
  - temp
  - webapps
  - work

## 项目部署

部署项目有以下三种方式。

1、直接将项目放置到`webapps`目录下

2、修改`conf/server.xml`文件

```XML
      <!-- ... -->

      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <!-- ↓↓↓新增部分↓↓↓ -->
        <Content docBase="系统目录" path="映射URL"/>
        <!-- ↑↑↑新增部分↑↑↑ -->

      </Host>
    </Engine>
  </Service>
</Server>
```

3、新建`conf/Catalina/localhost/映射URL.xml`文件

```XML
<!-- ↓↓↓新增部分↓↓↓ -->
<Content docBase="系统目录"/>
<!-- ↑↑↑新增部分↑↑↑ -->
```

## 项目目录结构

- ProjectRoot
  - WEB-INF
    - web.xml
    - classes
    - lib
