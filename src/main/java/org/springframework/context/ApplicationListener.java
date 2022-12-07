package org.springframework.context;

import java.util.EventListener;

/**
 * @author gjd3
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
  void onApplicationEvent(E event);
}
