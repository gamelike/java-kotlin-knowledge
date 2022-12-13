package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gjd3
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
    implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }


  @Override
  public BeanDefinition getBeanDefinition(String name) {
    BeanDefinition beanDefinition = beanDefinitionMap.get(name);
    if (beanDefinition == null) {
      throw new BeansException("no bean name '" + name + "' is defined");
    }
    return beanDefinition;
  }

  @Override
  public void preInstantiateSingletons() throws BeansException {
    for (String beanName : beanDefinitionMap.keySet()) {
      //判断是否是单例
      if (beanDefinitionMap.get(beanName).isSingleton()) {
        getBean(beanName);
      }
    }
  }

  @Override
  public String[] getBeanDefinitionNames() {
    Set<String> beanNames = beanDefinitionMap.keySet();
    return beanNames.toArray(new String[0]); //会自动扩容
  }

  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
    Map<String, T> result = new HashMap<>();
    for (String beanName : beanDefinitionMap.keySet()) {
      BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
      Class beanClass = beanDefinition.getBeanClass();
      //判断是否是继承而来 多个方法都会调用此处
      if (type.isAssignableFrom(beanClass)) {
        T bean = (T) getBean(beanName);
        result.put(beanName, bean);
      }
    }
    return result;
  }

  @Override
  public boolean containsBeanDefinition(String beanName) {
    return beanDefinitionMap.containsKey(beanName);
  }
}
