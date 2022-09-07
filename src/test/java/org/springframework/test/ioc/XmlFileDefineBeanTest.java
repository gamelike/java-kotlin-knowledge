package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

/**
 * @author gjd
 */
public class XmlFileDefineBeanTest {
  @Test
  public void testXmlFile() throws Exception {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

    Person person = (Person) beanFactory.getBean("person");
    System.out.println(person);

    Car car = (Car) beanFactory.getBean("car");
    System.out.println(car);
  }
}
