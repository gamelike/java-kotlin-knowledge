package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * 实现此接口 能感知 所属的beanfactory
 * @author gjd3
 */
public interface BeanFactoryAware extends Aware {
  void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
