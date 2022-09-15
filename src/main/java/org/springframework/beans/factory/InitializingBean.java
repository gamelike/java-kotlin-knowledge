package org.springframework.beans.factory;

/**
 * 初始化bean
 * @author gjd
 */
public interface InitializingBean {
  /**
   * 初始化bean后设置属性
   * @throws Exception
   */
  void afterPropertiesSet() throws Exception;
}
