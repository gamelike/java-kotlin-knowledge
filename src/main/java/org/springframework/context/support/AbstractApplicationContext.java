package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author gjd
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
  @Override
  public void refresh() throws BeansException {
    //创建bean 工厂 加载 beanDefinition
    refreshBeanFactory();
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();




    //bean加载之前  更新beanDefinition
    invokeBeanFactoryPostProcessors(beanFactory);

    //添加 applicaitonContextAwareProcessor ,让继承感知接口的bean能感知bean  todo 应该在 修改完beanFactory后再注入更合适
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    //前置 后置处理bean
    registerBeanPostProcessors(beanFactory);

    //提前实例化单例bean
    beanFactory.preInstantiateSingletons();
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
    destroyBeans();
  }

  protected void destroyBeans() {
    getBeanFactory().destroySingletons();
  }
}
