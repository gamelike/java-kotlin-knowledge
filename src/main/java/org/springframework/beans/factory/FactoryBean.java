package org.springframework.beans.factory;

/**
 * @author gjd3
 */
public interface FactoryBean<T> {
  T getObject() throws Exception;

  boolean isSingleton();
}
