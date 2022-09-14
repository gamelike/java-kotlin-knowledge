package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * bean工厂的能力接口
 *
 * @author gjd
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
  /**
   * beanPostprocessor的前置处理办法
   *
   * @param existingBean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

  /**
   * beanPostprocessor的后置处理办法
   *
   * @param existingBean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
      throws BeansException;
}
