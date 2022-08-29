package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.service.HelloService;

/**
 * @author gjd3
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {
  @Test
  public void testBeanFactory() throws Exception {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
    beanFactory.registryBeanDefinition("helloService", beanDefinition);
    HelloService helloService = (HelloService) beanFactory.getBean("helloService");
    helloService.sayHello();
  }
}
