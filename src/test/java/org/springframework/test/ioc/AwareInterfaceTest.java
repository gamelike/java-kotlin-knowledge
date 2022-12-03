package org.springframework.test.ioc;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.service.HelloService;

/**
 * @author gjd3
 */
public class AwareInterfaceTest {
  @Test
  public void test() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
    Assertions.assertThat(helloService.getApplicationContext()).isNotNull();
    Assertions.assertThat(helloService.getBeanFactory()).isNotNull();
  }
}
