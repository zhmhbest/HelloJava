package org.example.base.collection;

import java.util.Set;
// 二差树实现、元素有序
import java.util.TreeSet; // --> NavigableSet ==> SortedSet ==> Set
// 哈希表实现、元素无序、可放入null值
import java.util.HashSet; // --> Set


public class HelloSet {
    public static void demoSet(Set<String> set) {
        set.add("Java");
        set.add("Python");
        set.add("Go");
        System.out.println(set.size());

        set.add("Java"); // 该元素将不会被添加
        System.out.println(set);

        set.remove("Java");
        System.out.println(set);
    }

    public static void demoTreeSet() {
        TreeSet<String> set = new TreeSet<>();
        demoSet(set);
    }

    public static void demoHashSet() {
        HashSet<String> set = new HashSet<>();
        demoSet(set);
    }

    public static void main(String[] args) {
        demoTreeSet();
        demoHashSet();
    }
}
