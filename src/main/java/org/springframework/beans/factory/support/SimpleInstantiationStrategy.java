package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author gjd3
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

  /**
   * 简单的bean实例化策略，根据bean的无参构造函数实例化对象
   * @param beanDefinition
   * @return
   * @throws BeansException
   */
  @Override
  public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
    Class beanClass = beanDefinition.getBeanClass();
    try {
      //beanClass.newInstance(); //调用空参构造方法
      Constructor constructor = beanClass.getDeclaredConstructor();
      return constructor.newInstance();
    }catch (Exception e){
      throw new BeansException("Failed to instance ["+beanClass.getName()+"]",e);
    }
  }
}
