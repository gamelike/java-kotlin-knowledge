package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;

/**
 * @author gjd3
 */
public class CircularReferenceWithProxyBeanTest {
  @Test
  public void testCircularReference() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
    A a = applicationContext.getBean("a", A.class);
    B b = applicationContext.getBean("b", B.class);

    //增加二级缓存不能解决有代理对象时的循环依赖。
    //todo a被代理，放进二级缓存earlysingletonObject是实例化后的A（但是是代理前），但是放进一级缓存的是被代理后A，实例化b时从early中获取a 故不相等
    System.out.println(b.getA() == a);
  }
}
