package org.example.base.collection;

import java.util.Iterator;
import java.util.Map;
// 散列表、单链表解决冲突、可放入null值
import java.util.HashMap;       // ==> AbstractMap --> Map
// 散列表、附加一个双向链表维护键值对的顺序
import java.util.LinkedHashMap; // ==> HashMap ==> AbstractMap --> Map
// 红黑树
import java.util.TreeMap;       // ==> AbstractMap --> Map
                                // --> NavigableMap ==> SortedMap ==> Map
// 散列表、单链表解决冲突、线程安全
import java.util.Hashtable;     // --> Map
                                // ==> Dictionary

public class HelloMap {
    public static void demoMap(Map<String, Integer> map) {
        // map.put(null, 0);
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
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            System.out.printf(
                    "%s=%d ", item.getKey(), item.getValue()
            );
        }
        System.out.print('\n');
    }

    public static void demoHashMap() {
        HashMap<String, Integer> map = new HashMap<>();
        demoMap(map);
    }

    public static void demoLinkedHashMap() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        demoMap(map);
    }

    public static void demoTreeMap() {
        TreeMap<String, Integer> map = new TreeMap<>();
        demoMap(map);
    }

    public static void demoHashtable() {
        Hashtable<String, Integer> map = new Hashtable<>();
        demoMap(map);
    }

    public static void main(String[] args) {
        demoHashMap();
        demoLinkedHashMap();
        demoTreeMap();
        demoHashtable();
    }
}
