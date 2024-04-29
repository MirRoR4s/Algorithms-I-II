# Stacks and Queues

## 总结

学习了栈和队列的实现方式，包括基于单链表和可变长数组的实现等。

后续提到了栈的简单应用，如利用栈实现中缀算术表达式的求解。

[幻灯片地址](https://www.coursera.org/learn/algorithms-part1/supplement/UAJbP/lecture-slides)

## Interview questions

### Queue with stacks

- 目标：利用两个栈实现队列

思路：

1. 将两栈分为出队栈和入队栈
2. 当入队时将元素压入入队栈中
3. 当出队时从入队栈逐个弹出元素并压入出队栈中直到入队栈还剩最后一个元素，此时将该元素弹出并返回。
4. 将出队栈中的所有元素依次压入入队栈中
5. 继续以上过程。

---

### Stack with max

- 目标：实现一个数据结构，支持栈的基本操作，比如入栈和出栈，但是还需支持返回栈中最大值的操作。（假设栈中的元素都是实数，所以此处就是返回栈中最大的实数）

思路：

1. 在数据结构中维护一个最大值变量 max ，初始值为 null
2. 当元素入栈时将其与 max 相比较，如果大于 max 则将 max 的值更新为入栈的元素。（如果 max 为 null，直接将 max 更新为入栈元素）
3. 出栈操作与普通的栈相同
4. 当执行返回栈中最大值操作时，依次从栈中弹出元素，直至其等于 max 为止。

后续：

官方的提示是用**两个栈**，其中一个栈专门用于存储最大值。

---

### Java generics

- 目标：解释为何 Java 禁止创建泛型数组

详情参见[知乎](https://www.zhihu.com/question/20928981)的这一系列文章

---

## 编程作业-[Deques and Randomized Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)

### 前言

- 目标：为双端队列和随机队列编写泛型数据类型。利用单链表和数组实现一些基本的数据结构，并学习泛型和迭代器的基本知识。
