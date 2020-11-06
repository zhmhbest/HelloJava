import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
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
