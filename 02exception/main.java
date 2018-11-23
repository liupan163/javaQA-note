//一、Exception和Error的区别？


//两者都继承自Throwable，只有throwable的实例才可以被（抛出throw）或者（捕获catch），处理异常的基本组合。
//Exception是程序正常运行中，可以预测的意外情况，应该捕获处理。
//exception又可以分为可检查异常和不检查异常，可检查异常在源代码里需要进行捕获处理，这是编译期检查的一部分。

//不检查异常就是所谓运行异常，类型NullPointException，ArrayIndexOutOfBoundsException之类。并不会在编译期强制要求。

//Error.
//eg:
//1、 如NoClassDefFoundError和ClassNotFoundException区别?
//NoClassDefFoundError是一个Error(不可恢复)，而ClassNotFoundException是一个异常(可恢复)
/*  原因：ClassNotFoundException---
    java支持用Class.forName方法来动态地加载类，任意一个类名如果被作为参数触底给这个方法都将导致该类被加载到JVM内存中，如果这个类
    在类路径中没有找到，报此异常。例如使用Class.forName,ClassLoader.loadClass方法来动态地加载类时。
    另外当一个类，已经被一个加载器加载到内存中，此时另一个类加载器又尝试着动态地从同一包中加载这个类。
    solution：1、检查路径和文件名 2、控制动态类加载过程可解决
*/
/*  原因：NoClassDefFoundError---
    如果JVM或者ClassLoader实例尝试加载（可以通过正常方法调用，也可能是new来创建新对象）类的时候找不到------类定义-----
    要查找的类:编译的时候是存在的，运行的时候找不到了，这时候就ClassNotFoundException
    大概率是打包时漏掉了，或者jar包出现损坏或者篡改。
    solution：检查路径下文件有没有
*/
//加载时从外部存储器找不到 ->ClassNotFoundException
//连接时从内存找不到      ->NoClassDefFoundException

//2、 throw new Exception{} 和 functon() throws Exception{}


//实操 --- Throw early,catct late.
//1、尽量不要捕获类似Exception这用的通用异常，而是要捕获特定异常，拒绝异常掩盖
//2、不要生吞异常。  假设异常不存在，忽略掉
//3、不要用e.printStackTrace() 生成系统中很难判断输出到哪去了。采用日志记录


//性能角度
//1、try-catch代码段会产生额外的性能开销，或者换个角度说，它往往会影响JVM对代码进行优化，所以建议仅捕获有必要的代码段，尽量不要一个大的
// try包住整段的代码；与此同时，利用异常控制代码流程，也不是一个好主意，远比我们通常意义上的条件语句（if/else、switch）要低效。
//2、Java每实例化一个Exception，都会对当时的栈进行快照，这是一个相对比较重的操作。如果发生的非常频繁，这个开销可就不能被忽略了。



/*
  note
  1 不要推诿或延迟处理异常，就地解决最好，并且需要实实在在的进行处理，而不是只捕捉，不动作。
  2 一个函数尽管抛出了多个异常，但是只有一个异常可被传播到调用端。最后被抛出的异常时唯一被调用端接收的异常，其他异常都会被吞没掩盖。
  如果调用端要知道造成失败的最初原因，程序之中就绝不能掩盖任何异常。
  3 不要在fnally代码块中处理返回值
  4 按照我们程序员的惯性认知：当遇到return语句的时候，执行函数会立刻返回。但是，在Java语言中，如果存在fnally就会有例外。除了return语句，
  try代码块中的break或continue语句也可能使控制权进入fnally代码块。
  5 请勿在try代码块中调用return、break或continue语句。万一无法避免，一定要确保fnally的存在不会改变函数的返回值。
  6 函数返回值有两种类型：值类型与对象引用。对于对象引用，要特别小心，如果在fnally代码块中对函数返回的对象成员属性进行了修改，即使
  不在fnally块中显式调用return语句，这个修改也会作用于返回值上
  7 勿将异常用于控制流。
  8 如无必要，勿用异常。
  */
