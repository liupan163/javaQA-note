
/*
 java8增加了函数式编程的支持，所以又增加了---类定义，即function interface-----简单说就是只有一个抽象方法的接口，通常用
 @FunctionalInterface Annotation来标记。Lambda表达式本身可以看作一类functional interface
 我们熟悉的Runnable、Callable之类，都是functional interface
*/

// java8以后，接口也是可以有办法实现的。 java8开始，interface增加了对default method的支持。
// java9以后，甚至可以定义private default method。default method 提供了一种二进制的扩展已有接口的办法。
// 比如，熟知的java.util.Collection, 在java8中增加了一系列的default method，主要是Lambda、Stream相关功能。

/*
SOLID原则
    single
    Open-close
    Liskov
    Interface Segregation
    Dependency Inversion
*/

//eg:
/* -----before-----
public class VIPCenter {
    void serviceVIP(T extend User user>) {
        if (user insanceof SlumDogVIP){
            // 穷X VIP，活动抢的那种
            // do somthing
        } else if (user insanceof RealVIP){
            // do somthing
        }
        // ...
    }
}
*/


/*-----after-----
public class VIPCenter {
    private Map<User.TYPE, ServiceProvider> providers;
    void serviceVIP(T extend User user） {
        providers.get(user.getType()).service(user);
    }
}
interface ServiceProvider{
    void service(T extend User user) ;
}
class SlumDogVIPServiceProvider implements ServiceProvider{
    void service(T extend User user){
        // do somthing
    }
}
class RealVIPServiceProvider implements ServiceProvider{
    void service(T extend User user) {
        // do something
    }
}
*/
