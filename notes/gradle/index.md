<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Gradle](../index.html)

[TOC]

## 配置镜像

```batch
REM Windows
notepad "%Userprofile%\.gradle\init.gradle"
```

```bash
# Cygwin
if [ ! -d ~/.gradle ]; then cmd /c mklink /j "$(cygpath -w ~/.gradle)" "$(cmd /c ECHO %Userprofile%\\.gradle)"; fi
vim ~/.gradle/init.gradle
```

```bash
# Linux
vim ~/.gradle/init.gradle
```

`init.gradle`

```java
// 配置仓库地址
allprojects {
    repositories {
        def ALIYUN_REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/groups/public'
        def ALIYUN_JCENTER_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
        all {
            ArtifactRepository repo ->
                if (repo instanceof MavenArtifactRepository) {
                    def url = repo.url.toString()
                    if (url.startsWith('https://repo1.maven.org/maven2')) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                        remove repo
                    }
                    if (url.startsWith('https://jcenter.bintray.com/')) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                        remove repo
                    }
                }
        }
        maven {
            url ALIYUN_REPOSITORY_URL
            url ALIYUN_JCENTER_URL
        }
    }
}
```

## 创建项目

```bash
# Shell
mkdir demo
cd demo
gradle init
```

```txt
Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Select test framework:
  1: JUnit 4
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit 4) [1..4]

Project name (default: demo):
Source package (default: demo):

BUILD SUCCESSFUL
2 actionable tasks: 2 executed
```

```txt
demo:
│  build.gradle                                 <打包配置>
│  settings.gradle                              <项目配置>
│  gradlew                                      Wrapper自动生成
│  gradlew.bat                                  Wrapper自动生成
│
├─build                                         Build自动生成
│      *
│
├─.gradle                                       Build自动生成
│      *
│
├─gradle                                        Wrapper自动生成
│  └─wrapper
│          gradle-wrapper.jar
│          gradle-wrapper.properties
│
└─src                                           <源代码>
    ├─main
    │  ├─java
    │  │  └─demo
    │  │          App.java
    │  │
    │  └─resources
    └─test
        ├─java
        │  └─demo
        │          AppTest.java
        │
        └─resources
```

### 清理冗余

```bash
# 清理Build
rm -rf ./.gradle ./build

# 删除Wrapper
rm -rf ./gradle ./gradlew ./gradlew.bat
```

### wrapper

`gradle`、`gradlew`、`gradlew.bat`这三个文件由wrapper自动生成，用于在没有gradle的情况下自动下载，删除不会影响当前环境的构建。

```bash
# 运行
gradle run
gradle clean

# 添加Wrapper
gradle wrapper

# 添加Wrapper并指定版本
# gradle wrapper --gradle-version 6.2

# 运行
gradlew run
gradlew clean
```

## HelloGroovy

<!-- 
https://www.jianshu.com/p/5d30f1443aa6
@import "groovy/build.gradle" {code_block=true as='java' class='line-numbers'}
 -->

[`build.gradle`](groovy/build.gradle)

```bash
gradle -q HelloGroovy
```
