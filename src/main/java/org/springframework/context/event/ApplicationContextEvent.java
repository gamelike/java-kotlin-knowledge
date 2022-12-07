package org.springframework.context.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @author gjd3
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
  public ApplicationContextEvent(Object source) {
    super(source);
  }

  public final ApplicationContext getApplicationContext() {
    return (ApplicationContext) getSource();
  }
}
