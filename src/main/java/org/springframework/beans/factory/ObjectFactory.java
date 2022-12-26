package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author gjd3
 */
public interface ObjectFactory<T> {
  T getObject() throws BeansException;
}
