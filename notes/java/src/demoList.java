import java.util.List;
import java.util.Iterator;

/*
 * ArrayList
 * 长度不固定的数组，内部通过数组实现
 * 适合随机查找和遍历，不适合插入和删除
 * 内存不够时默认扩展(50%+1)个
 */
import java.util.ArrayList;

/*
 * Vector
 * 长度不固定的支持线程同步的数组，内部通过数组实现
 * 访问速度慢于ArrayList
 * 内存不够时默认扩展1倍
 */
import java.util.Vector;

/*
 * LinkedList
 * 链表结构存储数据
 * 适合动态插入和删除，随机访问速度较慢
 * 提供操作表头、表尾的方法，可以当作堆栈、队列和双向队列使用
 */
import java.util.LinkedList;

public class demoList {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        // List<String> buffer = new Vector<String>();
        // List<String> buffer = new LinkedList<String>();

        buffer.add("Java");
        buffer.add("Python");
        buffer.add("Go");
        buffer.add("C");
        buffer.add("C++");
        buffer.add("C#");
        buffer.add("Php");
        System.out.println(buffer.size());
        System.out.println(buffer);

        // 获取第一个
        System.out.println(buffer.get(0));

        // 设置第一个
        buffer.set(0, "Scala");
        System.out.println(buffer);

        // 删除第一个
        buffer.remove(0);
        System.out.println(buffer);

        // 判断元素是否存在
        System.out.println(buffer.contains("Java"));

        // 迭代1
        for(String item : buffer) {
            System.out.printf("%s ", item);
        }
        System.out.print('\n');

        // 迭代2
        Iterator<String> it = buffer.iterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.print('\n');
    }
}
