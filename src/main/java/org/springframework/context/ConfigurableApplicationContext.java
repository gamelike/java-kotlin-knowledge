package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * @author gjd
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
  /**
   * 刷新容器 核心方法
   *
   * @throws BeansException
   */
  void refresh() throws BeansException;
}
