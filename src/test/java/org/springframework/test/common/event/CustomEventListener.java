package org.springframework.test.common.event;

import org.springframework.context.ApplicationListener;

/**
 * @author gjd3
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
  @Override
  public void onApplicationEvent(CustomEvent event) {
    System.out.println("I am event: " + this.getClass().getName());
  }
}
