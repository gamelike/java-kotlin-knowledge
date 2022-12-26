package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
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

  //三级缓存
  private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

  private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

  @Override
  public Object getSingleton(String beanName) {
    Object singletonObject = singletonObjects.get(beanName);
    if (singletonObject == null) {
      singletonObject = earlySingletonObjects.get(beanName);
      if (singletonObject == null) {
        ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
        if (singletonFactory != null) { //如果存在代理 则用代理更新替换其他缓存
          singletonObject = singletonFactory.getObject();
          //三级缓存更新到二级缓存
          earlySingletonObjects.put(beanName, singletonObject);
          singletonFactories.remove(beanName);
        }
      }
    }
    return singletonObject;
  }

  public void addSingleton(String beanName, Object singletonObject) {
    singletonObjects.put(beanName, singletonObject);
    earlySingletonObjects.remove(beanName);
    singletonFactories.remove(beanName);
  }

  protected void addSingletonFactory(String beanName, ObjectFactory<?> singleFactory) {
    singletonFactories.put(beanName, singleFactory);
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
