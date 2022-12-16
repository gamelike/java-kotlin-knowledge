package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author gjd
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

  public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

  private ApplicationEventMulticaster applicationEventMulticaster;

  @Override
  public void refresh() throws BeansException {
    //创建bean 工厂 加载 beanDefinition
    refreshBeanFactory();
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();

    //添加 applicaitonContextAwareProcessor ,让继承感知接口的bean能感知bean
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

    //bean加载之前  更新beanDefinition
    invokeBeanFactoryPostProcessors(beanFactory);
    //前置 后置处理bean
    registerBeanPostProcessors(beanFactory);

    //初始化事件发布者
    initApplicationEventMulticaster();

    //注册事件监听器
    registerListeners();

    //提前实例化单例bean
    beanFactory.preInstantiateSingletons();

    //发布容器刷新完成事件
    finshResresh();
  }

  @Override
  public <T> T getBean(Class<T> requiredType) throws BeansException {
    return getBeanFactory().getBean(requiredType);
  }

  /**
   * 初始化事件发布者
   */
  private void initApplicationEventMulticaster() {
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
    beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
  }

  /**
   * 注册事件监听器
   */
  private void registerListeners() {
    Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
    for (ApplicationListener applicationListener : applicationListeners) {
      applicationEventMulticaster.addApplicationListener(applicationListener);
    }
  }


  /**
   * 发布初始化容器刷新事件
   */
  private void finshResresh() {
    publishEvent(new ContextRefreshedEvent(this));
  }

  /**
   * 发布事件
   *
   * @param event
   */
  @Override
  public void publishEvent(ApplicationEvent event) {
    applicationEventMulticaster.multicastEvent(event);
  }

  /**
   * bean实例化之前，执行beanFactoryPostProcessor
   *
   * @param beanFactory
   */
  protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
    for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
      beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
    }
  }

  /**
   * 注册beanPostProcessor
   *
   * @param beanFactory
   */
  protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
    for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
      beanFactory.addBeanPostProcessor(beanPostProcessor);
    }
  }


  @Override
  public String[] getBeanDefinitionNames() {
    return getBeanFactory().getBeanDefinitionNames();
  }

  @Override
  public Object getBean(String name) throws BeansException {
    return getBeanFactory().getBean(name);
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return getBeanFactory().getBean(name, requiredType);
  }

  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
    return getBeanFactory().getBeansOfType(type);
  }

  public abstract ConfigurableListableBeanFactory getBeanFactory();

  protected abstract void refreshBeanFactory() throws BeansException;

  @Override
  public void close() {
    doClose();
  }

  @Override
  public void registerShutdownHook() {
    Thread shutdown = new Thread(new Runnable() {
      @Override
      public void run() {
        doClose();
      }
    });
    Runtime.getRuntime().addShutdownHook(shutdown);
  }

  protected void doClose() {
    //发布容器关闭事件
    publishEvent(new ContextClosedEvent(this));

    //执行关闭bean
    destroyBeans();
  }

  protected void destroyBeans() {
    getBeanFactory().destroySingletons();
  }
}
