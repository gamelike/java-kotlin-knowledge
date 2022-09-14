package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author gjd
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
  /**
   * 加载 xml文件
   *
   * @param beanFactory
   * @throws BeansException
   */
  @Override
  protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
    String[] configLocations = getConfigLocations();
    if (configLocations != null) {
      xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
    }

  }

  protected abstract String[] getConfigLocations();
}
