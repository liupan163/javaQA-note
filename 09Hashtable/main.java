//Hashtable、HashMap、TreeMapd有什么不同？

/*都是常见的一些Map实现，以键值对的形式存储和操作数据的容器类型。

Hashtable早期的一个hash表实现，不用了。
HashMap最常用，支持null值。
TreeMap是基于红黑树的一种顺序访问的Map，他的操作get、put时间复杂度都是Olog（n）级别的,具体顺序由指定的Comparator来决定。*/

//1、理解Map相关类似整体结构，由其是有序数据结构的一些要点。
//2、从源码去分析HashMap的设计和实现要点，理解容量，负载引子等，为什么要加这些参数，如何影响map的性能，实践中如何取舍等。
//3、理解树化改造的相关原理和改进原因。

//另外，hashmap并发可能出现无限循环占用CPU，size不准确等问题。
//HashMap的性能表现非常依赖于hash码的有效性
//equal相等，hashCode一定要相等。
//重写了hashCode也要重写equals
//hashCode需要保持一致性，状态改变的hash值仍然要一致。
//equals的对称、反射、传递等特性。

//针对有序Map的分析，LinkedHashMap和TreeMap。
//LinkedHahsMap通常提供的是顺序遍历，符合插入顺序，他的实现是通过为条目维护一个双向链表。
//场景：构建一个空间占用敏感的资源池，希望不常被访问的元素释放掉。（删除操作）

//对于TreeMap，他的整体顺序是由键的顺序关系决定的，通过Comparator或Comparable（自然顺序）来决定。


//2HashMap源码分析
//a、HashMap内部实现基本点分析
//b、容量capacity和负载系数load factor
//c、树化
//内部结构：可以看做Node[] table和链表组合的符合结构，数组被分成一个个桶，通过哈希值决定了键值对在这个数组的寻址；哈希值相同的键值对，
//则以链表形式存储。  注意：如果链表大小超过阈值(TREEIFY_THRESHOLD,8),链表就会被改成树形结构。
