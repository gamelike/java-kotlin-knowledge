package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gjd3
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }


  @Override
  protected BeanDefinition getBeanDefinition(String name) {
    BeanDefinition beanDefinition = beanDefinitionMap.get(name);
    if (beanDefinition == null) {
      throw new BeansException("no bean name '" + name + "' is defined");
    }
    return beanDefinition;
  }
}
