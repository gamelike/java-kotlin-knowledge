package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;

/**
 * @author gjd
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
  /**
   * 在bean实例化之前执行
   *
   * @param beanClass
   * @param beanName
   * @return
   */
  Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

  /**
   * bean实例化之后，设置属性执行之前
   *
   * @param pvs
   * @param bean
   * @param beanName
   * @return
   */
  PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName);

  /**
   * bean实例化之后，设置属性之前执行
   *
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;
}
