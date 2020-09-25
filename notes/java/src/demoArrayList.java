/*
 * ArrayList: 是一个长度不固定的数组
 */
import java.util.ArrayList;

public class demoArrayList {
    public static void main(String[] args) {
        ArrayList<String> buffer = new ArrayList<String>();
        buffer.add("Java");
        buffer.add("Python");
        buffer.add("Go");
        buffer.add("C");
        buffer.add("C++");
        buffer.add("C#");
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

        // 迭代
        for(String item : buffer) {
            System.out.print(String.format("%s ", item));
        }
    }
}
