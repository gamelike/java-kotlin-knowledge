package org.springframework.beans.factory;

/**
 * 销毁bean’
 *
 * @author gjd
 */
public interface DisposableBean {
  void destroy() throws Exception;
}
