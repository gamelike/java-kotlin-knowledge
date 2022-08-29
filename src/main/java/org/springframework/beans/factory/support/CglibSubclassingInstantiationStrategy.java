package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author gjd3
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

  /**
   * 使用CGLIB动态生成子类
   * @param beanDefinition
   * @return
   * @throws BeansException
   */
  @Override
  public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
    return new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
  }
}
