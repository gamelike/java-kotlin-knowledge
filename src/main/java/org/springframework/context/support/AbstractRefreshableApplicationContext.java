package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author gjd
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
  private DefaultListableBeanFactory beanFactory;

  /**
   * 创建beanFactory 并加载BenanDefinition
   *
   * @throws BeansException
   */
  protected final void refreshBeanFactory() throws BeansException {
//    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    DefaultListableBeanFactory beanFactory = createBeanFactory();
    loadBeanDefinitions(beanFactory);
    this.beanFactory = beanFactory;
  }

  protected DefaultListableBeanFactory createBeanFactory() {
    return new DefaultListableBeanFactory();
  }

  /**
   * 加载beanDefinition
   *
   * @param beanFactory
   * @throws BeansException
   */
  protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

  @Override
  public DefaultListableBeanFactory getBeanFactory() {
    return beanFactory;
  }
}
