import java.util.Queue;
import java.util.LinkedList;
// import java.util.Deque;
// import java.util.ArrayDeque;
// import java.util.PriorityQueue;

public class demoCollectionQueue {
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
