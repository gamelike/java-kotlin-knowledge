package org.springframework.test.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.service.WorldService;

/**
 * @author gjd
 */
public class AutoProxyTest {
  @Test
  public void testAutoProxy() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
    WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
    worldService.explode();
  }

  @Test
  public void testPopulateProxyBeanWithPropertyValues() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:populate-proxy-bean-with-property-values.xml");
    WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
    worldService.explode();

    System.out.println(worldService.getName());
  }
}
