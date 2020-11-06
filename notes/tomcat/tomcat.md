## Tomcat

```txt
apache-tomcat:
├─bin
│      shutdown.sh                  catalina.sh start
│      startup.sh                   catalina.sh stop
│      catalina.sh                  启动并传递参数给bootstrap.jar
│      bootstrap.jar                类加载器
│      commons-daemon.jar           服务容器
│      tomcat-juli.jar              日志功能
│      ...
│
├─conf
│  │  catalina.policy
│  │  catalina.properties
│  │  context.xml
│  │  jaspic-providers.xml
│  │  jaspic-providers.xsd
│  │  logging.properties
│  │  server.xml
│  │  tomcat-users.xml
│  │  tomcat-users.xsd
│  │  web.xml
│  │
│  └─Catalina
│      └─localhost
│              *.xml
│
├─lib
│      *.jar
│
├─logs                              日志目录
├─temp                              暂存目录
├─webapps                           所有Web应用
└─work                              编译后的JSP文件
    └─Catalina
        └─localhost
```

### Catalina

#### 环境变量

```txt
JAVA_HOME           Java Development Kit installation
JRE_HOME            Java Runtime installation
CLASSPATH

JAVA_OPTS           Java runtime options used when the "jpda start"
JPDA_TRANSPORT
JPDA_ADDRESS
JPDA_SUSPEND

CATALINA_HOME       Catalina "build" directory
CATALINA_BASE       For resolving dynamic portions
CATALINA_TMPDIR     Temporary directory
CATALINA_OPTS       Java runtime options used when the "start" | "run" | "debug"

LOGGING_CONFIG      Override Tomcat's logging config file
LOGGING_MANAGER     Override Tomcat's logging manager
```

#### 启动参数

```txt
debug               Start Catalina in a debugger
debug -security     Debug Catalina with a security manager
jpda start          Start Catalina under JPDA debugger
run                 Start Catalina in the current window
run -security       Start in the current window with security manager
start               Start Catalina in a separate window
start -security     Start in a separate window with security manager
stop                Stop Catalina
configtest          Run a basic syntax check on server.xml
version             What version of tomcat are you running?
```

#### 一般启动

```bash
java \
    -Djava.util.logging.config.file="${CATALINA_BASE}/conf/logging.properties" \
    -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager \
    -Djdk.tls.ephemeralDHKeySize=2048 \
    -Djava.protocol.handler.pkgs=org.apache.catalina.webresources \
    -Dignore.endorsed.dirs="" \
    -classpath "${CATALINA_HOME}/bin/bootstrap.jar;${CATALINA_HOME}/bin/tomcat-juli.jar" \
    -Dcatalina.base="${CATALINA_BASE}" \
    -Dcatalina.home="${CATALINA_HOME}" \
    -Djava.io.tmpdir="${CATALINA_BASE}/temp" \
    org.apache.catalina.startup.Bootstrap \
    start
```

#### TomcatRunner

此方法用于兼容**IDEA Community**。

- *Step1*：新建**Java Module**命名为**Server**。
- *Step2*：引入依赖`${CATALINA_HOME}/bin/bootstrap.jar`、`${CATALINA_HOME}/bin/tomcat-juli.jar`。
- *Step3*：够建文件[`TomcatResources.java`](./Server/src/TomcatResources.java)、[`TomcatRunner.java`](./Server/src/TomcatRunner.java)及基本配置[`runner.properties`](./Server/src/conf/runner.properties)、[`server.xml`](./Server/src/conf/server.xml)

```txt
Server:
│
├─out
└─src
    │  TomcatResources.java
    │  TomcatRunner.java
    │
    └─conf
            runner.properties
            server.xml
            ...
```

- *Step4*：编辑`server.xml`文件完善配置。
