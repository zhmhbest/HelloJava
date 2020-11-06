import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
// import java.util.Vector;
// import java.util.LinkedList;

public class demoCollectionList {
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
