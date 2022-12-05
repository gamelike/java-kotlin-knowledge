package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author gjd3
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

  private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

  private final Map<String, Object> factoryBeanObejctCache = new HashMap<>();

  @Override
  public Object getBean(String name) throws BeansException {
    Object sharedInstance = getSingleton(name);
    if (sharedInstance != null) {
      //如果是factoryBean 从FactoryBean#getObject 创建Bean
      return getObjectForBeanInstance(sharedInstance, name);
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    Object bean = createBean(name, beanDefinition);
    return getObjectForBeanInstance(bean, name);
  }

  /**
   * 如果是FactoryBean,从FactoryBean#getObject 创建Bean
   *
   * @param beanInstance
   * @param beanName
   * @return
   */
  protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
    Object object = beanInstance;
    if (beanInstance instanceof FactoryBean) {
      FactoryBean factoryBean = (FactoryBean) beanInstance;

      try {
        if (factoryBean.isSingleton()) {
          object = this.factoryBeanObejctCache.get(beanName);
          if (object == null) {
            object = factoryBean.getObject();
            this.factoryBeanObejctCache.put(beanName, object);
          }
        } else {
          //多例 创建bean
          object = factoryBean.getObject();
        }
      } catch (Exception e) {
        throw new BeansException("FactoryBean threw exception on object[ " + beanName + " ] creation", e);
      }
    }
    return object;
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
