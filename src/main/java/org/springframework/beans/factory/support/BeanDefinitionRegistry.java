package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 负责注册beanDefinition
 *
 * @author gjd3
 */
public interface BeanDefinitionRegistry {
  /**
   * 向注册表注册BeanDefinition
   *
   * @param beanName
   * @param beanDefinition
   */
  void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
