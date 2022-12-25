package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringValueResolver;

/**
 * @author gjd
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
  /**
   * 添加后置执行？
   *
   * @param beanPostProcessor
   */
  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

  /**
   * 销毁单例bean
   */
  void destroySingletons();

  void addEmbeddedValueResolver(StringValueResolver valueResolver);

  String resolveEmbeddedValue(String value);

  ConversionService getConversionService();

  void setConversionService(ConversionService conversionService);
}
