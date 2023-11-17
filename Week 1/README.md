# 作业

## 作业1

作业地址：https://coursera.cs.princeton.edu/algs4/assignments/hello/specification.php

问题：windows 下怎样在命令行中压缩获得 zip 文件？

解决方案：我后续查证了一下，使用 [7z](https://www.7-zip.org/download.html) 这款软件即可。用法参看[此处](http://blog.haoji.me/windows-cmd-zip.html?from=xa)。

1. 从标准输入读取一系列单词并以均等概率打印某个单词
2. 不要把这些单词存在一个列表或是数组中
3. 使用 Knuth 的方法，当读取第 i 个单词时，以 1/i 的概率选择该单词成为“冠军”并替换之前的“冠军”，在读取完所有单词后，打印最后的“冠军”
