# Week2 学习总结

## Stacks and Queues

1. 学习如何基于链表和数组实现栈和队列，
2. 学习栈的简单应用，如利用栈求解中缀算术表达式。

> [幻灯片地址](https://www.coursera.org/learn/algorithms-part1/supplement/UAJbP/lecture-slides)

pdf 有一个抽象的概念-`Constant Amortized Time` 常量摊销时间，在 [stack overflow](https://stackoverflow.com/questions/200384/what-is-constant-amortized-time) 上有相应的讲解

### Interview questions

#### Queue with stacks

- 目标：利用两个栈实现队列

思路：

1. 将两栈分为出队栈和入队栈
2. 当入队时将元素压入入队栈中
3. 当出队时从入队栈逐个弹出元素并压入出队栈中直到入队栈还剩最后一个元素，此时将该元素弹出并返回。
4. 将出队栈中的所有元素依次压入入队栈中
5. 继续以上过程。

---

#### Stack with max

- 目标：实现一个数据结构，支持栈的基本操作，比如入栈和出栈，但是还需支持返回栈中最大值的操作。（假设栈中的元素都是实数，所以此处就是返回栈中最大的实数）

思路：

1. 在数据结构中维护一个最大值变量 max ，初始值为 null
2. 当元素入栈时将其与 max 相比较，如果大于 max 则将 max 的值更新为入栈的元素。（如果 max 为 null，直接将 max 更新为入栈元素）
3. 出栈操作与普通的栈相同
4. 当执行返回栈中最大值操作时，依次从栈中弹出元素，直至其等于 max 为止。

后续：

官方的提示是用**两个栈**，其中一个栈专门用于存储最大值。

---

#### Java generics

- 目标：解释为何 Java 禁止创建泛型数组

详情参见[知乎](https://www.zhihu.com/question/20928981)的这一系列文章

---

### 编程作业-[Deques and Randomized Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)

- 目标：编写泛型双端队列和随机队列。利用单链表和数组实现一些基本的数据结构，并学习泛型和迭代器的基本知识。

#### Deque

- 目标：实现泛型双端队列，能够在队首和队尾出入队元素。
- 运行时间要求：每个队列操作在最坏情况下的运行时间都是常量的；迭代器的每个操作（包括构造）在最坏情况下的运行时间都是常量的
- 运行内存要求：长为 n 的队列至多只能使用 48n + 192 字节内存

##### 实现

- 根据每个队列操作在最坏情况下运行时间都为常量，可知应该采用链表的形式来实现。

1. 设置内部类 Node，包含 Item data 和 Node prev、Node back 实例变量，prev 指向当前节点的上一个节点，back 指向当前节点的下一个节点。
2. 维护两个指针 Node first 和 Node last，分别指向队列的第一个元素和最后一个元素。
3. 维护一个变量 int size，记录队列长度。

---

#### RandomizedQueue

- 目标：实现一个泛型随机队列，能够均匀随机地出队元素。
- 运行时间要求：队列的每个操作的摊销时间是常量；迭代器的 `next()` 和 `hasNext()` 最坏情况运行时间是常量，构造函数则是线性阶。
- 运行内存要求：长为 n 的随机队列至多使用 48n + 192 字节内存；对每个迭代器会用到线性阶的额外内存

##### 数组实现

- 维护变量 `size` 记录队列长度。
- 维护数组 `randomizedQueue[]` 存储队列元素。
- 维护变量 `tail` 为下一次插入队列中的元素在数组中的索引。
- enqueue()：在 `randomizedQueue[tail]` 处入队。
- dequeue()：随机生成一个 `[0, size)` 之间的整数 index，出队 `randomizedQueue[index]`，调整数组，将 index 索引后的所有元素前移一位。

> 对 tail 的更新要对数组长度取模
> 出入队需更新 size

算法的问题是每一次出队都要调整数组使其连续，故而时间复杂度过高，无法满分通过测试。

---

#### Permutation

目标：输入一个整数 k，随后从命令行输入一系列字符串，最后均匀且随机地从输入的字符串中选出 k 个并打印出来。（序列中的每个字符串至多打印一次）

> 保证输入的字符串个数 n 大于 k

性能要求：运行时间和输入大小呈线性增长；仅使用常量内存再加上一大小最多为 n 的 Deque 或 RandomizedQueue 对象所占内存。

---

## Elementary Sorts

### Interview Questions

#### intersection of two sets

- 设计一个 subquadratic （次二次）的算法计算两集合并集的大小。

使用希尔排序！

```java
sort(a); // 排序集合a
sort(b); // 排序集合 b
if (b[0] != a[0]) {
    return 0;
}
int cnt = 1; // 统计并集元素个数
Item data = b[0]; // 记录上一次加入到并集中的元素
// 假设 b 是小的那个集合
for (int i = 1; i < b.length; b++) {
    if (b[i] == a[i] && b[i] != data) {
        cnt += 1;
        data = b[i];
    }

}
return cnt;
```

---

#### Permutation

- 设计一个次二次算法判断 b 数组是否是 a 数组的一个排列，也就是说 a、b 包含完全相同的元素，只不过元素的出现顺序不同。

思路：若 a、b 并集的大小也等于 n，则说明 b 是 a 的排列。

---

#### Dutch national flag

目标：有一个包含了 n 个 buckets 的数组，buckets 中装着不同颜色的鹅卵石，根据鹅卵石的颜色排序数组。

性能要求：

1. 至多调用 n 次 color()
2. 至多调用 n 次 swap()
3. 常量级别的额外空间

```java

for (int i = 0; i < n; i++) {
    for(int j = i; j > 0; j--) {
        if (a[j] < a[j-1]) {
            swap(a[j], a[j-1]);
        }
        else break;
    }
}


```