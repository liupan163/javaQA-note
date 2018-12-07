package design;
//Q:synchronized和ReentrantLock有什么区别？

/*
    ReentrantLock翻译为再入锁
    当一个线程试图获取一个它已经获取的锁时，这个获取动作就自动成功，这是对锁获取粒度的一个概念。
    也就是说，锁的持有是以线程为单位而不是基于调用次数。
    同时还有公平性的概念

    //ReentrantLock fairLock = new ReentrantLock(true)
*/

//如果说ReentrantLock是synchronized的替代选择，Condition则将是wait、notify、notifyAll等操作转化为相应的
//对象，将复杂而晦涩的同步操作转变为直观可控的对象行为。

//通过wait/signal组合，完成了条件判断和通知等待线程，非常顺畅就完成了状态流转。

