package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.convert.ConversionService;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author gjd3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
    implements AutowireCapableBeanFactory {

  private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition) {
    //如果bean需要代理，直接返回代理对象
    Object bean = resolveBeforeInstantiation(name, beanDefinition);
    if (bean != null) {
      return bean;
    }
    return doCreateBean(name, beanDefinition);
  }

  /**
   * 执行InstantiationAwareBeanPostProcessor的方法，如果bean需要代理，直接返回代理对象
   *
   * @param beanName
   * @param beanDefinition
   * @return
   */
  private Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
    //对bean实例化前加代理
    Object bean = applyBeanPostProcessorsBeforeInstantiation(beanName, beanDefinition.getBeanClass());
    if (bean != null) {
      //对代理后的bean  加值
      bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
    }
    return bean;
  }

  /**
   * @param beanName
   * @param beanClass
   * @return
   */
  private Object applyBeanPostProcessorsBeforeInstantiation(String beanName, Class beanClass) {
    List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessorList();
    for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
      if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
        Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition);

      //解决循环以来，放入缓存中提前暴露
      if (beanDefinition.isSingleton()) {
        earlySingletonObjects.put(name, bean);
      }

      //实例化bean之后执行
      boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(name, bean);
      if (!continueWithPropertyPopulation) {
        return bean;
      }

      //设置属性之前，允许beanPostProcessor修改属性值
      applyBeanPostBeforeApplyingPropertyValues(name, bean, beanDefinition);

      //为bean 填充属性
      applyPropertyValues(name, bean, beanDefinition);
      //执行bean初始化时候 执行 beanPostProcessor 的前置和后置方法
      bean = initializeBean(name, bean, beanDefinition);
    } catch (Exception e) {
      throw new BeansException("Instantion of bean failed", e);
    }

    //注册有销毁方法的bean

    registerDisposableBeanIfNecessary(name, bean, beanDefinition);

    if (beanDefinition.isSingleton()) {
      addSingleton(name, bean);
    }
    return bean;
  }

  /**
   * bean实例化后执行，如果返回false，不执行后续设置属性的逻辑
   *
   * @param name
   * @param bean
   * @return
   */
  private boolean applyBeanPostProcessorsAfterInstantiation(String name, Object bean) {
    boolean continueWithPropertyPopulation = true;
    for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
      if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
        if (!((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessAfterInstantiation(bean, name)) {
          continueWithPropertyPopulation = false;
          break;
        }
      }
    }
    return continueWithPropertyPopulation;
  }


  /**
   * 修改beanDefinition中的值
   *
   * @param name
   * @param bean
   * @param beanDefinition
   */
  private void applyBeanPostBeforeApplyingPropertyValues(String name, Object bean, BeanDefinition beanDefinition) {
    for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
      if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
        PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, name);
        if (pvs != null) {
          for (PropertyValue propertyValue : pvs.getPropertyValueList()) {
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
          }
        }
      }
    }
  }

  private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
    if (beanDefinition.isSingleton()) {
      if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
        registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
      }
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
        } else {
          //类型转换
          Class<?> sourceType = value.getClass();
          Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
          ConversionService conversionService = getConversionService();
          if (conversionService != null) {
            if (conversionService.canConvert(sourceType, targetType)) {
              value = conversionService.convert(value, targetType);
            }
          }
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
    if (bean instanceof BeanFactoryAware) {
      ((BeanFactoryAware) bean).setBeanFactory(this);
    }

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
    //TODO 后面会实现
    if (bean instanceof InitializingBean) {
      ((InitializingBean) bean).afterPropertiesSet();
    }
    String initMethodName = beanDefinition.getInitMethodName();
    if (StrUtil.isNotEmpty(initMethodName)) {
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
