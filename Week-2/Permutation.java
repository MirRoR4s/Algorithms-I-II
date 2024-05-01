import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> deque = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            deque.enqueue(StdIn.readString());
        }
        // 从队列中均匀随机地选出 k 个字符串并打印
        int cnt = 0;
        while (cnt != k) {
            System.out.println(deque.dequeue());
            cnt += 1;
        }

    }
}
