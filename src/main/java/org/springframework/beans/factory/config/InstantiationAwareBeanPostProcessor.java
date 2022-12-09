package org.springframework.beans.factory.config;

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
}
