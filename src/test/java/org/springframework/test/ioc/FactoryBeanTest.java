package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author gjd3
 */
public class FactoryBeanTest {
  @Test
  public void testFactoryBean() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
    Car car = applicationContext.getBean("car", Car.class);
    Assert.assertEquals("porsche", car.getBrand());
  }
}