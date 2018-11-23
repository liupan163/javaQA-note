
//一、谈谈你对java品台的理解？？？

//面向对象(封装、继承、多态)
//语言(泛型，lambda表达式,反射)
//平台无关性（JVM运行.class 文件）
//类库(集合、IO/NIO、网络、并发JUC、安全)
//JDK(java开发工具，包括jre，javac，诊断工具)

//write once ,run anywhere
//跨平台，垃圾回收，不用担心内存分配和回收的问题

/*JVM 设计上看，一个解耦的功能（应用和操作系统间）
动态编译，类加载机制，jdk1.8内嵌的Class-Loader
垃圾回收基本原理
JVM内存模型：堆、栈、方法区

通常分编译期和运行时。
编译期：.class ---> 文件编译成字节码,而不是可直接运行的机器码
运行时：jvm通过类加载器加载字节码，解释或者编译执行。jdk8实际是一种解释和编译混合的模式

jvm启动时，指定不同参数。比如-Xint，只解释执行，不对代码进行编译。（解释器是逐条读入，逐条解释运行的）
对应额-Xcomp，关闭解释器。
较新的编译方式即AOT，直接将字节码编译成机器码，避免了JIT预热等各方面额开销。Oracle JDK9就引入了AOT特性。工具jaotc
        jaotc --output libHelloWorld.so HelloWorld.class
jaotc --output libjava.base.so --module java.base
启动时直接指定就可以了。
        java -XX:AOTLibrary=./libHelloWorld.so,./libjava.base.so HelloWorld*/




//二、java是解释执行吗？

//.class文件经过JVM解析或者编译运行。
//(1)解析：.class文件经过jvm内嵌的解析器解析执行。
//(2)编译：存在jit（及时编译器），把经常运行的 热点代码，编译成本地相关机器码，并进行各种层次优化.
//(3)AOT编译器：java 9 提供的直接将所有代码编译成机器码。

//点
//A：跨平台是虚拟机的功劳，不同平台的虚拟机，提供不同平台环境。jvm负责将.class 文件转成目标机器码
//B：编译型语言：C/C++、 Pascal（Delphi）
//编译就是把源代码（高级语言，人类容易读，容易理解）转换成机器码（CPU能理解，能高效的执行）
//解释型语言：JavaScript、Perl、Python、Ruby
//解释就简单多了，解析源代码，并且直接执行，没有编译过程
//编译程序是整体编译完了，再一次性执行。 而解释程序是一边解释，一边执行

/*
jit是运行时才做的，需要预热才知道哪些是热点；
aot是编译期，静态的，直接编成类似类库的东西*/

//双亲委派类加载?