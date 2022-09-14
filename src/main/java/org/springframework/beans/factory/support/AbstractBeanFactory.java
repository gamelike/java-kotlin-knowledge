package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjd3
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

  private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

  @Override
  public Object getBean(String name) throws BeansException {
    Object bean = getSingleton(name);
    if (bean != null) {
      return bean;
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    return createBean(name, beanDefinition);
  }

  protected abstract Object createBean(String name, BeanDefinition beanDefinition);

  protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

  @Override
  public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
    //有则覆盖
    this.beanPostProcessorList.remove(beanPostProcessor);
    this.beanPostProcessorList.add(beanPostProcessor);
  }

  public List<BeanPostProcessor> getBeanPostProcessorList() {
    return beanPostProcessorList;
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return (T) getBean(name);
  }
}
