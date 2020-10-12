<link rel="stylesheet" href="https://zhmhbest.gitee.io/hellomathematics/style/index.css">
<script src="https://zhmhbest.gitee.io/hellomathematics/style/index.js"></script>

# [Gradle](../index.html)

[TOC]

## Hello

>[Download Gradle](https://gradle.org/releases/)

`build.gradle`

```js
task hello {
    doLast {
        println 'Hello!'
    }
}
```

```batch
gradle -q hello
```

```txt
Hello!
```

`build.gradle`

```js
task hello {
    String hello = 'Hello!'
    println hello.toUpperCase()
}
```

```batch
gradle -q hello
```

```txt
HELLO!
```
