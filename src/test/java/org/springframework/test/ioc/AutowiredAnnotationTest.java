package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Person;

/**
 * @author gjd
 */
public class AutowiredAnnotationTest {
  @Test
  public void run() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowired-annotation.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person.getCar());
  }
}
