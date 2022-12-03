package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * 实现此接口 能感知所属的applicationContext
 *
 * @author gjd3
 */
public interface ApplicationContextAware {
  void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
