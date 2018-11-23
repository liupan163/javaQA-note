//final finally finalize有什么不同？
final Lis
<String> srLis=new ArrayLis<>();
    srLis.add("Hello");
    srLis.add("world");
    Lis
    <String> unmodifableStrLis=Lis.of("hello","world");
        unmodifableStrLis.add("again");

        final  智能约束strList这个引用不被赋值，但这个对象的行为是不受影响的

        immutable在很多场景是非常棒的选择，某种意义上说，java语言目前并没有原生的不可变支持，如果要实现immutable的类。
        1、将class自身声明为final，这样别人就不能扩展来绕过限制了
        2、将所有成员变量定义为private和final，并且不要实现setter方法
        3、构造对象时，成员变量使用深度拷贝来初始化，而不是直接赋值。因为我们无法控制输入对象不被其他人修改。
        4、如果确实需要实现getter方法，或者其他可能会返回内部状态的方法，使用COW（copy-on-write）原则，创建私有copy。


        //finalize诟病的原因
        finalize被设计在对象被垃圾收集前调用---本质上，finalize成为了快速回收的阻碍者。
        finalize拖慢垃圾收集，导致大量垃圾堆积，导致OOM。
        //替换者java.lang.ref.Cleaner----实现了引用幻象，这是一种常见的post-mortem清理机制。利用幻象引用和引用队列，我们
        可以保证对象在被彻底销毁前，做一些类似资源回收的动作。他比finalize更加轻量、更加可靠。
        吸取了finalize里的教训，每个Cleaner都是独立的

        //幻象引用????


        //QA
        匿名内部类访问局部变量时，局部变量定义为final
        java inner class实际会copy一份，不是直接去使用局部变量，final可以防止数据一致性问题。
