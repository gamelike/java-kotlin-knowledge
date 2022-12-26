package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gjd3
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  //一级缓存
  private Map<String, Object> singletonObjects = new HashMap<>();

  //二级缓存 直接创建出来就会被放进来，会存在可能放置的是代理前的对象
  protected Map<String, Object> earlySingletonObjects = new HashMap<>();

  private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

  @Override
  public Object getSingleton(String beanName) {
    Object bean = singletonObjects.get(beanName);
    if (bean == null) {
      bean = earlySingletonObjects.get(beanName);
    }
    return bean;
  }

  public void addSingleton(String beanName, Object singletonObject) {
    singletonObjects.put(beanName, singletonObject);
  }

  public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
    disposableBeans.put(beanName, disposableBean);
  }

  /**
   * 销毁单例
   */
  public void destorySingletons() {
    Set<String> beanNames = disposableBeans.keySet();
    for (String beanName : beanNames) {
      DisposableBean disposableBean = disposableBeans.remove(beanName);
      try {
        disposableBean.destroy();
      } catch (Exception e) {
        throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
      }
    }
  }
}
