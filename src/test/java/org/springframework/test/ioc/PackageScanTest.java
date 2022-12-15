package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;

/**
 * @author gjd
 */
public class PackageScanTest {
  @Test
  public void run() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");
    Car car = applicationContext.getBean("car", Car.class);
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(car);
    System.out.println(person);
  }
}
