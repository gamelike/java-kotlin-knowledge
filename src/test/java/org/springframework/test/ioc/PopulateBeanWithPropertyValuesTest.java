package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.bean.Person;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gjd3
 */
public class PopulateBeanWithPropertyValuesTest {
  @Test
  public void testPopulateBeanWithPropertyValues() throws Exception {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("name","gjd"));
    propertyValues.addPropertyValue(new PropertyValue("age",18));
    BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
    beanFactory.registryBeanDefinition("person",beanDefinition);

    Person person = (Person) beanFactory.getBean("person");
    System.out.println(person);

    assertThat(person.getName()).isEqualTo("gjd");
    assertThat(person.getAge()).isEqualTo(18);
  }

}
