import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;

/*
 * HashMap
 * 根据键的Hash值存储数据
 * 遍历时取得数据的顺序是完全随机的
 * 最多只允许一条记录的Key为null，多条Value为null
 * 不支持线程的同步
 */
import java.util.HashMap;

/*
 * LinkedHashMap
 * 保存了记录的插入顺序
 * 一般情况下遍历时会比HashMap慢
 */
import java.util.LinkedHashMap;

/*
 * TreeMap
 * 根据键值排序，默认按键值的升序排序
 */
import java.util.TreeMap;

/*
 * Hashtable
 * 无论Key还是Value都不能为null
 * 线程安全的
 */
import java.util.Hashtable;

public class demoMap {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(null, 0);
        map.put("A", 10);
        map.put("B", 11);
        map.put("C", 12);
        map.put("D", 13);
        map.put("E", 14);
        map.put("F", 15);
        System.out.println(map);

        // 迭代1
        for (String key : map.keySet()) {
            System.out.printf(
                    "%s=%d ", key, map.get(key)
            );
        }
        System.out.print('\n');

        // 迭代2
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.printf(
                    "%s=%d ", key, map.get(key)
            );
        }
        System.out.print('\n');

        // 迭代3
        for (Entry<String, Integer> item : map.entrySet()) {
            System.out.printf(
                    "%s=%d ", item.getKey(), item.getValue()
            );
        }
        System.out.print('\n');
    }
}
