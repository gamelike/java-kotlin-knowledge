package org.springframework.test.ioc.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author gjd3
 */
public class CustomEvent extends ApplicationEvent {
  public CustomEvent(Object source) {
    super(source);
  }
}
