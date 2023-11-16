# Coursera-Algorithms-I-II

## 环境配置

环境配置有时可能比较麻烦，此时应仔细阅读指南。

## Week 1

地址：https://www.coursera.org/learn/algorithms-part1/home/week/1

### Course Introduction

这个 Hello World 差点给我整不会了，先是环境搭建浪费好久，代码编写执行的时候也出了问题。主要在于我的命令行下输入 enter 不代表标准输入的结束，这样程序一直卡住无法输出。最后看讨论别人告诉我说可以试试 ctrl+z 或者 ctrl+d，后面发现 ctrl+z 可以结束。

附上我的代码：

```java
public class HelloGoodbye {
    public static void main(String[] args) {

        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");

    }
}

```



```java
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

```