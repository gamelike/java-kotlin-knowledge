package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author gjd3
 */
public class ContextRefreshedEvent extends ApplicationEvent {
  public ContextRefreshedEvent(Object source) {
    super(source);
  }
}
