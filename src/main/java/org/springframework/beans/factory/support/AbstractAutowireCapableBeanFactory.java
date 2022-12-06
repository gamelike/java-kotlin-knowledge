package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

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

    //注册有销毁方法的bean
    registerDisposableBeanIfNecessary(name, bean, beanDefinition);

    addSingleton(name, bean);
    return bean;
  }

  private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
    // 如果是 销毁bean  或者 其中 xml配置的销毁方法不为空，则注册销毁bean
    if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
      registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
    }
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

    try {
      invokeInitMethod(beanName, wrapperdBean, beanDefinition);
    } catch (Throwable e) {
      throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
    }

    //执行后置处理
    wrapperdBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
    return wrapperdBean;
  }

  /**
   * 为bean执行初始化方法
   *
   * @param beanName
   * @param bean
   * @param beanDefinition
   */
  protected void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
    //初始化方法时候，去判断是不是 实现了初始化bean
    if (bean instanceof InitializingBean) {
      ((InitializingBean) bean).afterPropertiesSet();  //执行 初始化bean的初始化方法
    }
    String initMethodName = beanDefinition.getInitMethodName();
    if (StrUtil.isNotEmpty(initMethodName)) {   //对于 xml配置了初始化方法的，执行其初始化方法
      Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
      if (initMethod == null) {
        throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
      }
      initMethod.invoke(bean);
    }
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

  @Override
  public void destroySingletons() {
//    destroySingletons();
    //todo
    destorySingletons();
  }
}
