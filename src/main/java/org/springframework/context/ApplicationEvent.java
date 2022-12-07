package org.springframework.context;

import java.util.EventObject;

/**
 * @author gjd3
 */
public class ApplicationEvent extends EventObject {
  public ApplicationEvent(Object source) {
    super(source);
  }
}
