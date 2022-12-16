package org.springframework.context.annotation;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Field;

/**
 * @author gjd3
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

  private ConfigurableListableBeanFactory beanFactory;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return null;
  }

  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
    return null;
  }

  @Override
  public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
    //处理@Value注解
    Class<?> clazz = bean.getClass();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      Value valueAnnotation = field.getAnnotation(Value.class);
      if (valueAnnotation != null) {
        String value = valueAnnotation.value();
        value = beanFactory.resolveEmbeddedValue(value);
        BeanUtil.setFieldValue(bean, field.getName(), value);
      }
    }

    //处理autowired
    for (Field field : fields) {
      Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
      if (autowiredAnnotation != null) {
        Class<?> fieldType = field.getType(); //根据autowired的注解选择的类，对应其对象进行初始化
        Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
        String dependentBeanName = null;
        Object dependentBean = null;
        if (qualifierAnnotation != null) {
          dependentBeanName = qualifierAnnotation.value();
          dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
        } else {
          dependentBean = beanFactory.getBean(fieldType);
        }
        BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
      }
    }
    return pvs;
  }
}
