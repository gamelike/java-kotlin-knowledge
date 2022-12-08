package org.springframework.test.ioc;

import cn.hutool.core.lang.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author gjd3
 */
public class PrototypeBeanTest {
  @Test
  public void testPrototype() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
    Car car = applicationContext.getBean("car", Car.class);
    Car car2 = applicationContext.getBean("car", Car.class);
    Assert.isTrue(car != car2);
  }
}
