# HelloJava

```bash
    -cp         目录/zip/jar搜索路径
    -classpath  目录/zip/jar搜索路径（可指定多个）

    -Xloggc:<file>  将GC状态记录在文件中(带时间戳)
    -Xms<size>      初始Heap大小（默认物理内存的1/64）
    -Xmx<size>      最大Heap大小（默认物理内存的1/4）
    -Xss<size>      每个线程的Heap大小（默认1m）

    # 堆大小 = 年轻代大小 + 年老代大小 + 持久代大小
    -Xmn<size>                  -XX:newSize=<size> + -XX:MaxnewSize==<size>
    -XX:NewSize=<size>          年轻代初始内存（应小于ms）
    -XX:MaxnewSize=<size>       年轻代最大内存（应小于mx）
    -XX:PermSize=<size>         持久代初始大小
    -XX:MaxPermSize=<size>      持久代最大大小
    -XX:NewRatio=<size>         年轻代:年老代的比值=1:<size>
```
