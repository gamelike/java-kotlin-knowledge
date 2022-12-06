package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author gjd3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition) {
    return doCreateBean(name, beanDefinition);
  }

  protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
    Class beanClass = beanDefinition.getBeanClass();
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition);
    } catch (Exception e) {
      throw new BeansException("Instantion of bean failed", e);
    }
    addSingleton(name, bean);
    return bean;
  }

  protected Object createBeanInstance(BeanDefinition beanDefinition) {
    return getInstantiationStrategy().instantiate(beanDefinition);
  }

  public InstantiationStrategy getInstantiationStrategy() {
    return instantiationStrategy;
  }

  public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
    this.instantiationStrategy = instantiationStrategy;
  }
}
