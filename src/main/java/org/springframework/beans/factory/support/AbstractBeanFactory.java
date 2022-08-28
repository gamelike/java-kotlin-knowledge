package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author gjd3
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
  @Override
  public Object getBean(String name) throws BeansException {
    Object bean = getSingleton(name);
    if (bean != null) {
      return bean;
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    return createBean(name,beanDefinition);
  }

  protected abstract Object createBean(String name, BeanDefinition beanDefinition);

  protected abstract BeanDefinition getBeanDefinition(String name);
}
