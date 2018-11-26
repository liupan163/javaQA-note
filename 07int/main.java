//int和Integer有什么区别？

int是我们常说的整形数字，是java8个原始数据类型（boolean,byte,short,char,int,float,double,long）---虽然java语言号称一切是对象。

Integer是int对应的包装类，他有一个int类型的字段存储数据，并且提供了基本操作，比如数学运算、int和字符串之间的转换等。
静态工厂方法：valueOf，会利用一个缓存机制，带来性能改进。值默认：-128到127之间的会进行缓存。（需要是对象）


原始数据类型和java泛型不能配合使用---自动装箱auto-boxing，开箱unboxing

注意事项：
1基本类型都有取值范围，在大数*大数时候，可能会出现越界的情况。
2基本类型转换时，显式声明。  long result = 12345678L*24*25
3慎用基本类型存储货币。
4优先基本类型。
5涉及线程安全，建议采用AtomicInteger、AtomicLong这样的线程安全类。

