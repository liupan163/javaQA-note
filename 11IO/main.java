//java提供了哪些IO方式？NIO如何实现多路复用？

//java IO方式有很多种，基于不同的IO抽象模型和交互方式。
/*
第一、传统的java.io包，基于流模型实现，提供了我们最熟知的IO功能，如：file抽象、输入输出流等。
交互方式是同步、阻塞的方式。
java.io包的好处是代码比较简单、直观，缺点是IO效率和扩展性存在局限性
很多时候，把java.net下面提供的部分网络API，如：Socket、ServerSocket、HttpURLConnection也归类到同步阻塞IO类库，因为网络通信同样是IO行为。

第二、java 1.4中引入了NIO框架，提供了channel、Selector、Buffer等新的抽象，可以构建多路复用、同步非阻塞IO程序，同时提供了更接近
操作系统底层的性能处理方式。

第三、java 7中，NIO有了进一步的改进，NIO2,引入了异步非阻塞IO方式，AIO AsynchronousIO。
异步IO操作基于事件和回调机制，可以简单理解为，应用操作直接返回，而不会阻塞在那里，当后台处理完成，操作系统会通知相应线程进行后续工作。
*/

//给定场景，分别用不同模型实现，分析BIO、NIO等模型的设计和实现原理。
//NIO提供的高性能数据操作方式是基于什么原理，如何使用？

//---具体---
/*
IO不仅仅是对文件的操作，网络编程，如Socket通信都是典型IO操作目标。
输入流、输出流OutputStream是用于读写字节的，如操作图片文件。
Reader/Writer则是用于操作字符的。
BufferedOutputStream带缓冲区的实现，避免频繁的磁盘读写，进而提高IO处理效率。  务必记得flush。
很多IO工具类都实现了Closeable，因为需要对资源进行释放。如：打开FileInputStream，就会获得相应FileDescriptor，需要用
try-catch-resources、try-finally等机制保证FileInputStream被明确关闭，进而相应文件描述也会失败，否则将导致资源无法释放。
*/

//1、Java NIO概览
/*
a)Buffer，高效的数据容器，除了布尔类型，所有原始数据类型都有相应的Buffer实现。
b)Channel，类似Linux之类操作系统上看到的文件描述符，是NIO中被用来支持批量式IO操作的一种抽象。
c)File或者Socket，通常被认为是比较高层次的抽象，而Channel则是更加操作系统底层的一种抽象，这也使得NIO得以充分利用现代操作系统底层
机制，获得特定场景的性能优化，如，DMA（Direct Memory Access）等。不同层次的抽象是相互关联的，我们可以通过Socket获取Channel。
d)Selector，是NIO实现多路复用的基础，它提供了一种高效的机制，可以检测到注册到Selector上的多个Channel中，是否有Channel处于就绪
状态，进而实现了单线程对多Channel的高效管理。
e)Charset，提供Unicode字符串定义，NIO也提供了响应的编解码器，如：通过下面的方式进行字符串到ByteBuffer的转换：
  Charset.defaultCharset().encode("hello world!");
*/

//2、NIO能解决什么问题？
