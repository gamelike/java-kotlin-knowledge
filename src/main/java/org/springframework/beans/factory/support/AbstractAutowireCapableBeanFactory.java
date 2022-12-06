package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author gjd3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition) {
    return doCreateBean(name, beanDefinition);
  }

  protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition);
    } catch (Exception e) {
      throw new BeansException("Instantion of bean failed", e);
    }
    applyPropertyValues(name, bean, beanDefinition);
    addSingleton(name, bean);
    return bean;
  }

  /**
   * 实例话bean
   */
  protected Object createBeanInstance(BeanDefinition beanDefinition) {
    return getInstantiationStrategy().instantiate(beanDefinition);
  }


  /**
   * 为bean填充属性
   *
   * @param beanName
   * @param bean
   * @param beanDefinition
   */
  protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
    try {
      for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertys()) {
        String name = propertyValue.getName();
        Object value = propertyValue.getValue();

        //通过反射设置值
        BeanUtil.setFieldValue(bean, name, value);
      }
    } catch (Exception e) {
      throw new BeansException("Error setting property values for bean: " + beanName, e);
    }
  }

  public InstantiationStrategy getInstantiationStrategy() {
    return instantiationStrategy;
  }

  public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
    this.instantiationStrategy = instantiationStrategy;
  }
}
