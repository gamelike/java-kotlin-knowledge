package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gjd
 */
public class InitAndDestoryMethodTest {
  @Test
  public void testInitAndDestroyMethod() throws Exception {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    applicationContext.getBean("person");
    applicationContext.registerShutdownHook();
  }
}
