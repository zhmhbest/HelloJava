package org.example.base.collection;

import java.util.Queue;
// 循环数组
import java.util.ArrayDeque;    // --> Deque ==> Queue
// 小顶堆
import java.util.PriorityQueue; // ==> AbstractQueue --> Queue
// 双向链表
import java.util.LinkedList;    // --> Deque ==> Queue


public class HelloQueue {
    public static void demoQueue(Queue<String> queue) {
        queue.add("Java");
        queue.add("Python");
        queue.add("Go");
        queue.add("C");
        queue.add("C++");
        queue.add("C#");
        queue.add("Php");
        System.out.printf(
                "queue=%s; peek=%s; size=%d\n", queue, queue.peek(), queue.size()
        );
        while (! queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    public static void demoArrayDeque() {
        ArrayDeque<String> queue = new ArrayDeque<>();
        demoQueue(queue);
    }

    public static void demoPriorityQueue() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        demoQueue(queue);
    }

    public static void demoLinkedList() {
        LinkedList<String> queue = new LinkedList<>();
        demoQueue(queue);
    }

    public static void main(String[] args) {
        demoArrayDeque();
        demoPriorityQueue();
        demoLinkedList();
    }
}
