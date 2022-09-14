package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

/**
 * @author gjd3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
    implements AutowireCapableBeanFactory {

  private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition) {
    return doCreateBean(name, beanDefinition);
  }

  protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition);
      //为bean 填充属性
      applyPropertyValues(name, bean, beanDefinition);
      //执行bean初始化时候 执行 beanPostProcessor 的前置和后置方法
      initializeBean(name, bean, beanDefinition);
    } catch (Exception e) {
      throw new BeansException("Instantion of bean failed", e);
    }
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

        if (value instanceof BeanReference) {
          //beanA 以来beanB 先实例化BeanB
          BeanReference beanReference = (BeanReference) value;
          value = getBean(beanReference.getBeanName());
        }
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

  protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
    //执行前置方法
    Object wrapperdBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

    //TODO 后面会在此处执行bean的初始化方法
    invokeInitMethod(beanName, wrapperdBean, beanDefinition);

    //执行后置处理
    wrapperdBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
    return wrapperdBean;
  }

  /**
   * 为bean执行初始化方法
   *
   * @param beanName
   * @param wrapperdBean
   * @param beanDefinition
   */
  protected void invokeInitMethod(String beanName, Object wrapperdBean, BeanDefinition beanDefinition) {
    //TODO 后面会实现
    System.out.println("执行bean[" + beanName + "]的初始化方法");
  }

  @Override
  public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    for (BeanPostProcessor processor : getBeanPostProcessorList()) {
      Object current = processor.postProcessBeforeInitialization(result, beanName);
      if (current == null) {
        return result;
      }
      result = current;
    }
    return result;
  }

  @Override
  public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    for (BeanPostProcessor processor : getBeanPostProcessorList()) {
      Object current = processor.postProcessAfterInitialization(result, beanName);
      if (current == null) {
        return result;
      }
      result = current;
    }
    return result;
  }

}
