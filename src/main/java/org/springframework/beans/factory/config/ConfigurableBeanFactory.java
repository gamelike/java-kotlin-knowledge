package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author gjd
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
  /**
   * 添加后置执行？
   *
   * @param beanPostProcessor
   */
  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

  /**
   * 销毁单例bean
   */
  void destroySingletons();
}
