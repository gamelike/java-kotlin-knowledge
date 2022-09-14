package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * @author gjd
 */
public interface BeanPostProcessor {
  /**
   * 在bean初始化值前执行
   *
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

  /**
   * 在bean初始化之后执行
   *
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
