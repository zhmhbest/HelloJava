package org.example.collection;

import java.util.Iterator;
import java.util.List;
// 动态数组
import java.util.ArrayList;     // --> List
// 动态数组、线程安全
import java.util.Vector;        // --> List
import java.util.Stack;         // ==> Vector --> List
// 双向链表
import java.util.LinkedList;    // --> List


public class HelloList {
    public static void demoList(List<String> list) {
        list.clear();
        //
        list.add("Java");
        list.add("Python");
        list.add("Go");
        list.add("C");
        list.add("C++");
        list.add("C#");
        list.add("Php");
        System.out.println(list);

        // 长度
        System.out.println(list.size());

        // 获取位置1内容
        System.out.println(list.get(0));

        // 插入到位置1
        list.set(0, "Scala");
        System.out.println(list);

        // 删除第一个
        list.remove(0);
        System.out.println(list);

        // 判断元素是否存在
        System.out.println(list.contains("Java"));

        // 迭代1
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.print('\n');

        // 迭代2
        for (String item : list) {
            System.out.printf("%s ", item);
        }
        System.out.print('\n');
    }

    public static void demoArrayList() {
        ArrayList<String> list = new ArrayList<>();
        demoList(list);
    }

    public static void demoVector() {
        Vector<String> list = new Vector<>();
        demoList(list);
    }

    public static void demoLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        demoList(list);
    }

    public static void main(String[] args) {
        demoArrayList();
        demoVector();
        demoLinkedList();
    }
}
