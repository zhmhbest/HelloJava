import java.util.Set;
import java.util.HashSet;

public class demoCollectionSet {
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
