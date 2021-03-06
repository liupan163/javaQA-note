//对比Vector、ArrayList、LinkedList有何区别？


//高效地管理和操作数据是非常重要的。

//Vector是java早期提供的线程安全的动态数组，如果不需要线程安全，并不建议选择，毕竟同步是有额外开销。Vector内部是使用 对象数组 来保存数据的，
//可以根据需要自动增加容量，当数组已满时，会创建新的数组，并拷贝原有数组数据。

//ArrayList是应用更加广泛的--动态数组--实现，它本身 不是线程安全的，所以性能要好很多。同样，动态调整容量。

//LinkedList是java提供的双向链表，线程不安全。

//Vector和ArrayList作为动态数组，其内部元素以数组形式顺序存储----适合随机访问的场合。除了尾部插入和删除元素，往往性能相对较差，中间动一个
//元素，其他后面的都要改。

//而LinkedList进行节点插入、删除却要高效的多，随机访问性能则要比动态数组慢。


//二、需要实现一个云计算任务调度系统，希望可以保证VIP客户的任务被优先处理，你可以利用那些数据结构或者标准的集合类型呢？
//PriorityQueue