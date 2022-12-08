package org.springframework.test.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author gjd
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
    PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
    //修改
    propertyValues.addPropertyValue(new PropertyValue("name", "ivy"));
  }
}
