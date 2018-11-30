//NIO不只是多路复用，NIO2也不只是异步IO。
//一、java有几种文件拷贝方式？哪一种最高效？

//1 java.io库，FileInputStream、FileOutputStream
//2 或者：java.nio库transferTo、transferFrom
//3 java标准库本身已经提供了几种File.copy的实现。

//对于copy效率，总体说，NIO transferTo/From的方式可能更快，更能利用操作系统的底层机制。


//Q
//1、不同的copy方式，底层机制有什么不同？
//2、为什么零拷贝zero-copy可能有性能优势？
//3、Buffer分类与使用。
//4、Direct Buffer对垃圾收集等方面的影响与实践选择。

//A
//拷贝实现机制分析
/*
用户空间---内核空间
当我们使用输入输出流进行读写时，实际上是进行了多次的上下文切换。 当我们用输入输出流读写时，实际上是进行了多次上下文切换。比如应用读取数据时，
先在内核将数据从磁盘读取到内核缓存，再切换到用户态，将数据从内核读取到用户缓存。
所以，这种方式会带来一定的额外开销，可能会降低IO效率。

基于NIO transferTo的方式，在Linux和Unix上，则会使用到零拷贝技术，数据传输并不需要用户态 参与。
注意：transferTo不仅仅是可以用在文件拷贝中，与其类似的，利于磁盘读取文件，然后进行Socket发送，同样可以享受这种机制带来的性能提高。
*/

//零拷贝--->可以理解为内核态空间与磁盘之间数据传输，不需要经过用户空间

//java IO/NIO源码结构
//前面说的，java标注库也提供了文件拷贝的方法java.nio.file.Files.copy。

//掌握NIO Buffer
//Buffer是NIO操作数据的基本工具，java为没种原始数据类型都提供了相应的Buffer实现（布尔除外）。尤其是Direct Buffer，在垃圾回收时的特殊性。
/*

Buffer有几个基本属性：
        capacity：反映这个Buffer到底有多大，也就是数组长度。
        position：要操作的起始位置。
        limit：操作限额
        mark：上一次position的位置。
基本操作：
        我们创建了一个ByteBuffer，准备放入数据，capcity当然就是缓冲区大小，而position就是0，limit默认就是capcity的大小。
        当我们写入几个字节的数据时，position就会跟着水涨船高，但是它不可能超过limit的大小。
        如果我们想把前面写入的数据读出来，需要调用fip方法，将position设置为0，limit设置为以前的position那里。
        如果还想从头再读一遍，可以调用rewind，让limit不变，position再次设置为0
*/

//Direct Buffer和垃圾收集
//Direct Buffer：
//MappedByteBuffer：

/*
在实际使用中，java会尽量对Direct Buffer仅做本地IO操作，对于很多数据量的IO密集操作，会带来性能优势.原因：
        Direct Buffer生命周期内内存地址都不会发生改变，进而内核可以安全地进行访问，IO操作高效。
        减小了堆内存对象存储的可能维护工作，所以效率可能有所提高。
*/


//Q:如果我们需要在channel读取的过程中，将不同片段写入到相应的Buffer里面（类似二进制消息分拆成消息头、消息体等），可以采用NIO的什么机制呢？
/*
  A:可以利用NIO分散-scatter机制来写入不同buffer
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body = ByteBuffer.allocate(1024);
ByteBuffer[] bufferArray = {header,body};
channel.read(bufferArray);
该方法只适用于请求头长度固定
*/
