package org.springframework.test.ioc.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.test.ioc.bean.Car;

/**
 * @author gjd
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("BeanPostProcessor#postProcessBeforeInitialization");
    if ("car".equals(beanName)) {
      ((Car) bean).setBrand("lanbojiqi");
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
    return bean;
  }
}
