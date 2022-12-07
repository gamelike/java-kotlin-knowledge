package org.springframework.context;

/**
 * 用来发布事件
 *
 * @author gjd3
 */
public interface ApplicationEventPublisher {
  /**
   * 发布事件
   *
   * @param event
   */
  void publishEvent(ApplicationEvent event);
}
