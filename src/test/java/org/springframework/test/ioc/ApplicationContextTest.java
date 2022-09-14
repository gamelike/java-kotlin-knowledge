package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

/**
 * @author gjd
 */
public class ApplicationContextTest {
  @Test
  public void testApplication() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
    Car car = applicationContext.getBean("car", Car.class);
    Car car2 = (Car) applicationContext.getBean("car");
    System.out.println(car);
    System.out.println(car2);
  }
}
