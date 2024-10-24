package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author gjd
 */
public interface ListableBeanFactory extends BeanFactory {

  /**
   * 返回所有的beanname
   *
   * @return
   */
  String[] getBeanDefinitionNames();

  /**
   * 返回指定类型的所有实例
   *
   * @param type
   * @param <T>
   * @return
   * @throws BeansException
   */
  <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}