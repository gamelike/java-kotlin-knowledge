package org.springframework.beans.factory.config;

/**
 * @author gjd3
 */
public interface SingletonBeanRegistry {
  Object getSingleton(String beanName);
}
