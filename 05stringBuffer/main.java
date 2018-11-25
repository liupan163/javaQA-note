//Sting、StringBuffer、StringBuilder有什么区别?

//String是java语言非常基础和重要的类，提供了构造和管理字符串的各种基本逻辑。  典型的Immutable类，被声明成final class。
由于他的不可变性，类似拼接、裁剪等动作，都会产生新的String对象。

StringBuffer是为了解决String产生太过中间类而提供的一个类，用append或者add方法。本质是一个线程安全的可修改字符序列，他保证
了线程安全，也随之带来了性能开销，所以除非有性能安全要求。一般用StringBuilder。
StringBuilder是java1.5后新增的，在能力和StringBuffer没有区别，但是他去掉了线程安全的部分，有效减小了开销，是绝大部分情况
下进行字符串拼接的首选。

//设计考量
String的Immutable性质，决定了对象在拷贝时不需要额外复制数据。

再看StringBuffer,它的线程是通过各种修改数据方法加上synchronized关键字实现。
StringBuilder，底层都是利用可修改的（char，jdk9后是byte）数组，二者都继承了AbstractStringBuilder，里面包含了基本操作，区别仅在于最终的方法是否加了synchronize
另外这个内部数组的大小，不能过大，不能过小。目前默认16
