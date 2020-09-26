import java.util.Queue;

/*
 * Deque
 * 双端队列支持在两端插入和移除元素
 */
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

/*
 * PriorityQueue
 * 优先队列
 * 根据排序规则决定谁在队头，谁在队尾
 */
import java.util.PriorityQueue;

public class demoQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
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
}
