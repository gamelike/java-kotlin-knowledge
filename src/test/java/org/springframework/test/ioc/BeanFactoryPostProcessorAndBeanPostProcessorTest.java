package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;
import org.springframework.test.common.CustomBeanFactoryPostProcessor;
import org.springframework.test.common.CustomerBeanPostProcessor;

/**
 * @author gjd
 */
public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

  @Test
  public void testBeanFactoryPostProcessor() {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
    //如果提前创建过，就不会完成修改的目的
//    Person person = (Person) beanFactory.getBean("person");
//    System.out.println(person);
    //加载完毕后，修改具体值
    CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

    //获取
    Person person = (Person) beanFactory.getBean("person");
    System.out.println(person);
    Assert.assertTrue(person.getName().equals("ivy"));
  }

  @Test
  public void testBeanPostProcessor() {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

    CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
    beanFactory.addBeanPostProcessor(customerBeanPostProcessor);
    Car car = (Car) beanFactory.getBean("car");
    System.out.println(car);
    Assert.assertTrue(car.getBrand().equals("lanbojiqi"));
  }
}
