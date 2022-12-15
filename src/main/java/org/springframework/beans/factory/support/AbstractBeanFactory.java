package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author gjd3
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

  private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

  private final Map<String, Object> factoryBeanObejctCache = new HashMap<>();


  private List<StringValueResolver> embeddedValueResolvers = new LinkedList<>();

  @Override
  public Object getBean(String name) throws BeansException {
    // 从单例换从中读取一下
    Object sharedInstance = getSingleton(name);
    if (sharedInstance != null) {  //读不到，可能是factoryBean
      //如果是factoryBean 从FactoryBean#getObject 创建Bean
      return getObjectForBeanInstance(sharedInstance, name);
    }

    //读取其定义
    BeanDefinition beanDefinition = getBeanDefinition(name);
    Object bean = createBean(name, beanDefinition); //创建出来bean
    return getObjectForBeanInstance(bean, name); //再去尝试读取 factoryBean
  }

  /**
   * 如果是FactoryBean,从FactoryBean#getObject 创建Bean
   * 否则，返回beanInstance本身
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

  @Override
  public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
    this.embeddedValueResolvers.add(valueResolver);
  }

  @Override
  public String resolveEmbeddedValue(String value) {
    String result = value;
    for (StringValueResolver valueResolver : this.embeddedValueResolvers) {
      result = valueResolver.resolveStringValue(result);
    }
    return result;
  }
}
