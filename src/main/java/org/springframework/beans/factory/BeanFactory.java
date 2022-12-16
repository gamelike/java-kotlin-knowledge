package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * bean容器
 *
 * @author gjd3
 */
public interface BeanFactory {
  /**
   * 获取bean
   *
   * @param name
   * @return
   * @throws BeansException bean不存在时
   */
  Object getBean(String name) throws BeansException;

  <T> T getBean(Class<T> requiredType) throws BeansException;

  /**
   * 根据bean名称和类型实例bean
   *
   * @param name
   * @param requiredType
   * @param <T>
   * @return
   * @throws BeansException
   */
  <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
