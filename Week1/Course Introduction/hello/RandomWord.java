import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        int count = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;
            if (StdRandom.bernoulli(1.0 / count)) {
                // 在读取第 i 个单词时，以 1/i 的概率选择该单词替换当前冠军
                champion = word;
            }
        }
        StdOut.println(champion);
    }
}
