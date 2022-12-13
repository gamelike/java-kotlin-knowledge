package org.springframework.test.expanding;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Person;

/**
 * @author gjd3
 */
public class PropertyPlaceholderConfigurerTest {
  @Test
  public void test() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");
    Person person = (Person) applicationContext.getBean("person", Person.class);
    System.out.println(person.getName());
  }
}
