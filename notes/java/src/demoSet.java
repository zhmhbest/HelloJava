import java.util.Set;

/*
 * HashSet
 * 基于HashMap实现的不允许有重复元素的集合
 * 非线程安全的
 */
import java.util.HashSet;

public class demoSet {
    public static void main(String[] args) {
        HashSet<String> buffer = new HashSet<String>();
        buffer.add("Java");
        buffer.add("Python");
        buffer.add("Go");
        System.out.println(buffer.size());

        buffer.add("Java"); // 该元素将不会被添加
        System.out.println(buffer);

        buffer.remove("Java");
        System.out.println(buffer);
    }
}
