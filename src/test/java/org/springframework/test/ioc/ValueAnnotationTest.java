package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Person;

/**
 * @author gjd3
 */
public class ValueAnnotationTest {
  @Test
  public void run() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person.getAge());
    System.out.println(person);
  }
}
