package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;

/**
 * @author gjd3
 */
public class CircularReferenceWithoutProxyBeanTest {
  @Test
  public void testCircularReference() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
    A a = applicationContext.getBean("a", A.class);
    B b = applicationContext.getBean("b", B.class);
    System.out.println(a.getB() == b);
  }
}
