//java集合框架的典型容器类，他们大部分都不是线程安全的，仅有的线程安全实现，如Vector、Stack性能不够。

//JUC ,java.util.concurrent并发包的出现。

//一、如何保证容器是线程安全的？ConcurrentHashMap如何实现高效地线程安全？

//1、粗粒度的Synchronized，并发条件下，性能地下
//2、更普遍的是线程安全容器类

//各种并发容器，如：ConcurrentHashMap、CopyOnWriteArrayList
//各种线程安全队列Queue/Deque，如A仍然有BlockingQueue、SynchronousQueue。
//各种有序容器的线程安全版本等。

//A:为什么需要ConcurrentHashMap？
//Hashtable本身比较低效，他的get、put、操作都是通过synchronize。就是并发操作竞争一把锁，并发效率很低。

//HashMap并不是线程安全的，并发情况会导致类似CPU100%等问题，那为什么不用Collections提供的同步包装类来解决呢？
//看代码片段，同步包装器只是利用输入Map构造了另一个同步版本，所有操作虽然不在声明为synchronized，但还是利用了this作为互斥的mutex，没有真正
//意义上的改进

/*private static class SynchronizedMap<K,V> implements Map<K,V>, Serializable {
    private final Map<K, V> m; // Backing Map
    final Object mutex; // Object on which to synchronize

    // …
    public int size() {
        synchronized (mutex) {
            return m.size();
        }
    }
}*/
//--->所以，Hashtable和同步包装类版本，都只适合非高度并发的情况下

//B:ConcurrentHashMap分析
/*
早期ConcurrentHashMap，实现基于：
1分离锁：内部进行分段Segment，里面则是HashEntry数组，和HashMap类型，哈希相同的条目也是以链表形式存放。
2HashEntry内部使用volatile的value字段来保证可见性，也利用了不可变对象的机制以改进利用Unsafe提供的底层功能，如volatile access，去直接完成部分操作，
以最优化性能，毕竟Unsafe中的很多操作都是JVM intrinsic优化过的。
*/

//进行并发写操作时：
//1、ConcurrentHashMap会获取再入锁，以保证数据一致性，Segment本身就是基于RetrantLock的扩展，所以，在并发修改期间，相应的Segment被锁定的。
//2、在最初简短，进行重复性扫描，以确定相应key值是否已经在数组里面了，进而决定是更新还是放置操作。
//3、ConcurrentHashMap扩容是单独的Segment进行扩容。

/*
java8版本之后，ConcurrentHashMap发生的变化?
1总体结构上，类似HashMap，同样大的桶bucket数组，然后内部也是一个个链表结构bin，同步粒度要更细致。
2其内部仍然有Segment的定义，仅仅为序列化时的兼容性而已，不再有任何结构上的用处。
3因为不在用Segment，初始化大大简化，修改在lazy-load形式，减少了很多开销。
4数据存储利用volatile来保证--->可见性。
5使用CAS等操作，在特定场景 进行无锁并发操作。
6使用Unsafe、LongAdder之类的底层手段，进行极端情况的优化。
*/
