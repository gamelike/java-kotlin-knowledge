package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition的属性值
 *
 * @author gjd
 */
public interface BeanFactoryPostProcessor {
  /**
   * 在beanDefinition加载完成后，但在bean实例化之前，提供修改beanDefinition属性值的机制
   *
   * @param beanFactory
   * @throws BeansException
   */
  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
