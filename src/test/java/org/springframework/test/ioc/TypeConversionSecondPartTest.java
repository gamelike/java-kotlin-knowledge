package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author gjd3
 */
public class TypeConversionSecondPartTest {

  @Test
  public void run() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

    Car car = applicationContext.getBean("car", Car.class);
    System.out.println(car);
  }
}
